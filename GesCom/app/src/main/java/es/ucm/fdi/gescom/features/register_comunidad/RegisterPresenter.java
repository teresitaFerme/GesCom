package es.ucm.fdi.gescom.features.register_comunidad;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class RegisterPresenter extends BasePresenter {
    private RegisterView mRegisterView;
    private RegisterModel mRegisterModel;

    public RegisterPresenter(RegisterView registerView){
        mRegisterView = registerView;
        mRegisterModel = new RegisterModel((Context) registerView);
    }

    public void validateRegister(String commName, String username, String pss, String pssRepeat, String userDni) {
        //TODO meter validacion del formato de dni
        if (username.length() != 0 && commName.length() != 0 && pss.length() != 0 && pssRepeat.length() != 0) {
            if(pss.equals(pssRepeat)){//TODO: meterle aqui comprobaciones a la contraseña
                if (!mRegisterModel.getUsername(username)) {
                    if (!mRegisterModel.getCommunityName(commName)) {
                        long admin_id = mRegisterModel.registerUser(username, commName, pss, userDni, "Administrador");
                        if(admin_id != -1){
                            mRegisterModel.registerCommunity(commName, admin_id);
                            mRegisterView.registerSuccessful();
                        }
                        else mRegisterView.registerUserServerFailure();
                    } else mRegisterView.registerCommunityFailure();
                } else mRegisterView.registerExistingUser();
            }else mRegisterView.noMatchingPasswords();
        }else mRegisterView.fillingFailure();
    }
}
