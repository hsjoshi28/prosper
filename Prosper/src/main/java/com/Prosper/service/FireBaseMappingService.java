package com.Prosper.service;

import com.Prosper.response.model.FireBaseMapping;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class FireBaseMappingService {

    public static final String COL_NAME="users";

    public String savePatientDetails(FireBaseMapping patient) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(patient.getUserName()).set(patient);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public FireBaseMapping getPatientDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        FireBaseMapping patient = null;

        if(document.exists()) {
            patient = document.toObject(FireBaseMapping.class);
            return patient;
        }else {
            return null;
        }
    }
}