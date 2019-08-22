package com.clinicware.service;

import com.clinicware.data.RegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
@Service
public class UserService {

    private EntityManager entity;

    @Autowired
    public UserService(EntityManager entity) {
        this.entity = entity;
    }

    public RegisterValidation registerNewUser(String username, String password, String first, String last, String title, int userType, int entryUser, String device){
        StoredProcedureQuery query = entity.createStoredProcedureQuery("INS_MSYS_USERS_P")
                .registerStoredProcedureParameter("P_USERNAME", String.class, ParameterMode.IN)
                .setParameter("P_USERNAME", username)
                .registerStoredProcedureParameter("P_PASSWORD", String.class, ParameterMode.IN)
                .setParameter("P_PASSWORD", password)
                .registerStoredProcedureParameter("P_FIRST_NAME", String.class, ParameterMode.IN)
                .setParameter("P_FIRST_NAME", first)
                .registerStoredProcedureParameter("P_LAST_NAME", String.class, ParameterMode.IN)
                .setParameter("P_LAST_NAME", last)
                .registerStoredProcedureParameter("P_TITLE", String.class, ParameterMode.IN)
                .setParameter("P_TITLE", title)
                .registerStoredProcedureParameter("P_USER_TYPE", Integer.class, ParameterMode.IN)
                .setParameter("P_USER_TYPE", userType)
                .registerStoredProcedureParameter("P_ENTRY_USER", Integer.class, ParameterMode.IN)
                .setParameter("P_ENTRY_USER", entryUser)
                .registerStoredProcedureParameter("P_ENTRY_DEVICE", String.class, ParameterMode.IN)
                .setParameter("P_ENTRY_DEVICE", device)
                .registerStoredProcedureParameter("P_ID", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("P_TRX_STATUS", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("P_ERROR_MESSAGE", String.class, ParameterMode.OUT);

        query.execute();

        RegisterValidation validation = new RegisterValidation();
        validation.setId((Integer) query.getOutputParameterValue("P_ID"));
        validation.setStatus((Integer) query.getOutputParameterValue("P_TRX_STATUS"));
        validation.setErrorMessage((String) query.getOutputParameterValue("P_ERROR_MESSAGE"));
        System.out.println(validation);
        return validation;
    }

}
