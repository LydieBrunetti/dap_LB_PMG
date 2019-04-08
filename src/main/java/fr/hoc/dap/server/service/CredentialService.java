package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/** Service pour obtenir la liste des utilisateurs et leurs infos de connection. */
@Service
public class CredentialService extends GoogleService {

    /** méthode pour récupérer la user list.
     * @return la user list.
     * @throws GeneralSecurityException gestion des erreurs de sécurité.
     * @throws IOException gestion des erreurs IO. */
    public DataStore<StoredCredential> getUser() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();

        return datas;
    }

}
