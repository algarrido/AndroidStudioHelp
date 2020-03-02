package es.iesfranciscodelosrios.algarrido.wolfrol.presenters;

import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.AyudaInterface;

public class AyudaPresenter implements AyudaInterface.Presenter {

    private AyudaInterface.View view;

    public AyudaPresenter(AyudaInterface.View view){
        this.view=view;
    }
    @Override
    public void botonVolver() {
        view.volverListado();
    }
}

