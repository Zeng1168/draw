package controller;

import controller.drawMain.DrawMainController;
import view.DemodeView;

public class DemodeController implements DemodeView.drawmodelListener{
   private DemodeView demodeView;
   public DemodeController(){
       demodeView = new DemodeView();
       demodeView.setDrawModelListener(this);
   }

    @Override
    public void new_window() {
        new DrawMainController();
    }
}
