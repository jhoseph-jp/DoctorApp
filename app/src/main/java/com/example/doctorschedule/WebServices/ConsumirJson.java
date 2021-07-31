package com.example.doctorschedule.WebServices;

import com.example.doctorschedule.PagesConstructor.ClinicaObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsumirJson {

    public static List<ClinicaObject> jsonDados (String conteudo){

        try {
            List<ClinicaObject> clinicaObjectList = new ArrayList<>();
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;

            jsonArray= new JSONArray(conteudo);

            for (int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                ClinicaObject clinicaObject = new ClinicaObject();

                clinicaObject.setId(jsonObject.getString("id"));
                clinicaObject.setNome_clinica(jsonObject.getString("nome_clinica"));
                clinicaObject.setEndereco(jsonObject.getString("endereco"));
                clinicaObject.setTelefone(jsonObject.getString("telefone"));
                clinicaObject.setEmail(jsonObject.getString("email"));
                clinicaObject.setCreated_at(jsonObject.getString("created_at"));
                clinicaObject.setUpdated_at(jsonObject.getString("updated_at"));

                clinicaObjectList.add(clinicaObject);
            }
            return clinicaObjectList;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
