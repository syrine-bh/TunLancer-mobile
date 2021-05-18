/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.utils;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.LayeredLayout.LayeredLayoutConstraint;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author 
 */

public class ScrollBar extends Container implements ScrollListener {
     private final Container target;
    private final DragHandle dragHandle;
    
    
    private int orientation;
    public static final int X_AXIS=0;
    public static final int Y_AXIS=1;
    
    public ScrollBar(Container target, int orientation) {
        super(new LayeredLayout());
        setScrollableX(false);
        setScrollableY(false);
        this.orientation = orientation;
        this.target = target;
        this.target.addScrollListener(this);
        $(this).selectAllStyles()
                .setBorder(Border.createCompoundBorder(Border.createEmpty(), Border.createEmpty(), Border.createLineBorder(1, 0xcccccc), Border.createLineBorder(1, 0xcccccc)))
                .setBgColor(0xeaeaea)
                .setBgTransparency(128)
                .setPadding(0);
        
        dragHandle = new DragHandle();
        add(dragHandle);
        LayeredLayout ll = (LayeredLayout)getLayout();
        if (orientation == X_AXIS) {
            ll.setInsets(dragHandle, "0px auto 0px "+getInsetForScroll(target.getScrollX())+"px");
        } else {
            ll.setInsets(dragHandle, getInsetForScroll(target.getScrollY())+"px 0.25mm auto 0.25mm");
        }
    }

    int pressedX, pressedY;
    
    @Override
    public void pointerPressed(int x, int y) {
        super.pointerPressed(x, y); 
        pressedX = x;
        pressedY = y;
        if (!dragHandle.contains(x, y)) {
            int relY = y - getAbsoluteY();
            int targetY = (int)Math.round((relY - getStyle().getPaddingTop()) * (target.getLayoutHeight() - target.getHeight()) / 
                    ((float)getInnerHeight() - dragHandle.getOuterPreferredH()));
            if (target.getScrollY() > targetY) {
                int newScrollY = target.getScrollY() - target.getHeight();
                if (newScrollY < 0) {
                    newScrollY = 0;
                }
                target.scrollRectToVisible(target.getScrollX(), newScrollY, target.getWidth(), target.getHeight(), target);
            } else if (target.getScrollY() < targetY) {
                int newScrollY = target.getScrollY() + target.getHeight();
                if (newScrollY > target.getLayoutHeight() - target.getHeight()) {
                    newScrollY = target.getLayoutHeight() - target.getHeight();
                }
                target.scrollRectToVisible(target.getScrollX(), newScrollY, target.getWidth(), target.getHeight(), target);
            }
        }
    }

    @Override
    public void pointerDragged(int x, int y) {
        super.pointerDragged(x, y);
        if (!dragHandle.inDrag && !dragHandle.contains(x, y)) {
            int relY = y - getAbsoluteY();
            int targetY = (int)Math.round((relY - getStyle().getPaddingTop()) * target.getLayoutHeight() / ((float)getInnerHeight() - dragHandle.getOuterPreferredH()));
            if (targetY < 0) {
                targetY = 0;
            }
            if (targetY > target.getLayoutHeight() - target.getHeight()) {
                targetY = target.getLayoutHeight() - target.getHeight();
            }
            target.scrollRectToVisible(target.getScrollX(), targetY, target.getWidth(), target.getHeight(), target);
        }
    }

    

    
    
    
    @Override
    protected Dimension calcPreferredSize() {
        switch (orientation) {
            case X_AXIS:
                return new Dimension(target.getPreferredW(), px(2));
            default:
                int prefH = target.getPreferredH();
                return new Dimension(px(2), prefH);
        }
        
    }

    int lastTargetWidth, lastTargetLayoutWidth, lastTargetHeight, lastTargetLayoutHeight;
    @Override
    public void revalidate() {
        if (lastTargetWidth != target.getWidth() 
                || lastTargetHeight != target.getHeight() 
                || lastTargetLayoutHeight != target.getLayoutHeight() 
                || lastTargetLayoutWidth != target.getLayoutWidth()) {
            lastTargetWidth = target.getWidth();
            lastTargetHeight = target.getHeight();
            lastTargetLayoutHeight = target.getLayoutHeight();
            lastTargetLayoutWidth = target.getLayoutWidth();
            forceRevalidate();
            return;
        }
        super.revalidate();
    }

    
    
    
    @Override
    public void scrollChanged(int scrollX, int scrollY, int oldscrollX, int oldscrollY) {
        if (dragHandle.inDrag) {
            // We already updated ourselves
            return;
        }
        LayeredLayout ll = (LayeredLayout)getLayout();
        LayeredLayoutConstraint cnst = ll.getOrCreateConstraint(dragHandle);
        if (orientation == X_AXIS) {
            cnst.left().setPixels(getInsetForScroll(scrollX));
        } else {
            
            int y = getInsetForScroll(scrollY);
            if (y != cnst.top().getCurrentValuePx()) {
                cnst.top().setPixels(y);
                //System.out.println("Revalidating");
                //forceRevalidate();
                revalidate();
            }
        }
        //revalidate();
    }
    
    private int getInsetForScroll(int scroll) {
        LayeredLayout ll = (LayeredLayout)getLayout();
        LayeredLayoutConstraint cnst = ll.getOrCreateConstraint(dragHandle);
        if (orientation == X_AXIS) {
            return (int)Math.round(scroll * getInnerWidth() / (float)target.getLayoutWidth());
            
        } else {
            int out = (int)Math.round(scroll * getInnerHeight() / (float)target.getLayoutHeight());
            return Math.min(out, getInnerHeight() - dragHandle.getOuterPreferredH());
        }
    }
    /*
    private int convertScrollBarXToTargetX(int x) {
        return (int)Math.round(x * target.getLayoutWidth() / (float)getWidth());
    }
    
    private int convertScrollBarYToTargetY(int y) {
        return (int)Math.round((y + getStyle().getPaddingTop()) * target.getLayoutHeight() / ((float)getInnerHeight()));
    }
    
    private int convertTargetXToScrollBarX(int x) {
        return (int)Math.round(x * getWidth() / (float)target.getLayoutWidth());
    }
    
    private int convertTargetYToScrollBarY(int y) {
        return Math.max(0,
                Math.min(getInnerHeight() - dragHandle.getOuterPreferredH(),
                
                (int)Math.round(y * (getInnerHeight() - dragHandle.getOuterPreferredH()) / (float)target.getLayoutHeight())
                ));
    }
    */
    private class DragHandle extends Button {
        int startOuterY, startOuterX;
        int draggedX, draggedY, pressedX, pressedY;
        int targetPressedScrollX;
        int targetPressedScrollY;
        boolean inDrag;
        LayeredLayoutConstraint pressedConstraint;
        
        
        DragHandle() {
            RoundRectBorder border = RoundRectBorder.create()
                    .bezierCorners(false)
                    .cornerRadius(0.75f);
                    
            $(this).selectAllStyles().setBgColor(0xcccccc).setBgTransparency(200).setBorder(border)
                    .setPadding(0).setMargin(0);
            this.setDraggable(true);
            
        }

        @Override
        protected boolean isStickyDrag() {
            return true;
        }

        @Override
        protected Image getDragImage() {
            return null;
        }

        @Override
        protected void drawDraggedImage(Graphics g, Image img, int x, int y) {
            
        }

        @Override
        protected int getDragRegionStatus(int x, int y) {
            return orientation == X_AXIS ? Component.DRAG_REGION_IMMEDIATELY_DRAG_X : Component.DRAG_REGION_IMMEDIATELY_DRAG_Y;
        }

        @Override
        protected void dragFinished(int x, int y) {
            super.dragFinished(x, y); //To change body of generated methods, choose Tools | Templates.
            inDrag = false;
        }

        
        
        
        @Override
        public void pointerPressed(int x, int y) {
            super.pointerPressed(x, y);
            inDrag = true;
            targetPressedScrollX = target.getScrollX();
            targetPressedScrollY = target.getScrollY();
            startOuterY = dragHandle.getOuterY();
            startOuterX = dragHandle.getOuterX();
            pressedX = x;
            pressedY = y;
            LayeredLayout ll = (LayeredLayout)ScrollBar.this.getLayout();
            pressedConstraint = ll.getOrCreateConstraint(this).copy();
            pointerDragged(x, y); // so drag starts instantly
        }

        @Override
        public void pointerDragged(int x, int y) {
            super.pointerDragged(x, y); 
            setVisible(true);
            draggedX = x;
            draggedY = y;
            
            int deltaX = draggedX - pressedX;
            int deltaY = draggedY - pressedY;
            
            if (startOuterY + deltaY < ScrollBar.this.getStyle().getPaddingTop()) {
                deltaY = ScrollBar.this.getStyle().getPaddingTop() -startOuterY;
            }
            if (startOuterX + deltaX < ScrollBar.this.getStyle().getPaddingLeft(false)) {
                deltaX = ScrollBar.this.getStyle().getPaddingLeft(false)-startOuterX;
            }
            
            if (startOuterY + dragHandle.getOuterPreferredH() + deltaY > ScrollBar.this.getInnerHeight()) {
                deltaY = ScrollBar.this.getInnerHeight() - dragHandle.getOuterPreferredH() - startOuterY;
            }
            
            if (deltaY == 0 && orientation == Y_AXIS) {
                return;
            }
            
            if (deltaX == 0 && orientation == X_AXIS) {
                return;
            }
            if (pressedConstraint == null) {
                return;
            }
            LayeredLayoutConstraint cnst = pressedConstraint.copy();
            switch (orientation) {
                case X_AXIS:
                    cnst.left().translatePixels(deltaX, true, ScrollBar.this);
                    break;
                default:
                    cnst.top().translatePixels(deltaY, true, ScrollBar.this);
                    
                    
            }
            
            
            
            LayeredLayout ll = (LayeredLayout)ScrollBar.this.getLayout();
            cnst.copyTo(ll.getOrCreateConstraint(this));
            ScrollBar.this.revalidate();
            
            // Set the target's scroll position
            switch (orientation) {
                case X_AXIS:
                    //target.scrollRectToVisible(targetPressedScrollX + convertScrollBarXToTargetX(deltaX), targetPressedScrollY, target.getLayoutWidth(), target.getLayoutHeight(), target);
                    break;
                default:
                    int targetDeltaY = Math.round(deltaY * (target.getLayoutHeight() - target.getHeight())/((float)ScrollBar.this.getInnerHeight()-getOuterPreferredH()));
                    int targetY = targetPressedScrollY + targetDeltaY;
                    if (targetY + target.getHeight() > target.getLayoutHeight()) {
                        targetY = target.getLayoutHeight() - target.getHeight();
                    }
                    target.scrollRectToVisible(targetPressedScrollX, targetY , target.getWidth(), target.getHeight(), target);
            }
            
        }
        
        
        
        
        
        @Override
        protected Dimension calcPreferredSize() {
            int lHeight = target.getLayoutHeight();
            int iHeight = target.getHeight();
            if (iHeight == 0 || lHeight == 0) {
                //it hasn't been laid out yet.. lets wait and revalidate later
                Timer timer = new Timer();
                
                timer.schedule(new TimerTask() {
                    
                    @Override
                    public void run() {
                        $(()->{
                           DragHandle.this.setShouldCalcPreferredSize(true);
                           ScrollBar.this.revalidate(); 
                        });
                    }
                }, 30);
                
                return new Dimension(0, 0);
            }
            
            if (orientation == X_AXIS) {
                int h = Math.max(px(4), ScrollBar.this.getInnerHeight());
                if (target.getLayoutWidth() == target.getWidth() || !target.isScrollableX()) {
                    return new Dimension(0,0);
                }
                int w = (int)Math.round(ScrollBar.this.getInnerWidth() * ((float)target.getWidth()/target.getLayoutWidth()));
                return new Dimension(w, h);
            } else {
                int w = ScrollBar.this.getInnerWidth();
                if (target.getLayoutHeight() == target.getHeight() ) { // || target.isScrollableY()
                    return new Dimension(0,0);
                }
                int h = (int)Math.round(ScrollBar.this.getInnerHeight() * ((float)target.getHeight()/target.getLayoutHeight()));
                return new Dimension(w, h);
            }
        }
        
    }
    private int px(double mm) {
        return Display.getInstance().convertToPixels((float) mm);
    }
}
