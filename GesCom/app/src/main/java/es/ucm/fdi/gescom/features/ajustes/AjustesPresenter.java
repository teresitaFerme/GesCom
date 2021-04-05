package es.ucm.fdi.gescom.features.ajustes;

import androidx.appcompat.app.AppCompatDelegate;

import es.ucm.fdi.gescom.base.BasePresenter;

public class AjustesPresenter extends BasePresenter {
    private AjustesView mView;
    private AjustesModel mModel;

    AjustesPresenter(AjustesView view){
        mView = view;
        mModel = new AjustesModel();
    }

    public void changeMode() {
        // Get the night mode state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        //Set the theme mode for the restarted activity
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_YES);
        }
        // Recreate the activity for the theme change to take effect.
        mView.recreate();
    }
}
