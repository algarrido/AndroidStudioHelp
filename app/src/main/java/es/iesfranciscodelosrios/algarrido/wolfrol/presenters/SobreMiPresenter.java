package es.iesfranciscodelosrios.algarrido.wolfrol.presenters;

import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.SobreMiInterface;

public class SobreMiPresenter implements SobreMiInterface.Presenter {

    private SobreMiInterface.View view;

    public SobreMiPresenter(SobreMiInterface.View view){
        this.view=view;
    }
    @Override
    public void botonVolver() {
        view.volverListado();
    }
}

