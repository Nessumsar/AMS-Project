package com.example.ams_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CompareActivity extends AppCompatActivity{

    private Spinner filter;
    private ListView infoOfficerA;
    private ListView infoOfficerB;
    private Spinner officerA;
    private Spinner officerB;
    private DBHelper dbHelper;
    private ListView summaryA;
    private ListView summaryB;

    private OfficerModal sVictoria = new OfficerModal(1, "Victoria (S Class)", "Deployment");
    private SkillModal commandoHp = new SkillModal(1, "Commando HP", "Commando HP +240%. Takes effect when deployed", "Comando HP 240%");
    private SkillModal commandoDefense = new SkillModal(1, "Commando Defense", "Commando Defense 240%. Takes effect when deployed", "Commando Defense +240%");
    private SkillModal commandoAttack = new SkillModal(1, "Commando Attack", "Commando Attack 240%. Takes effect when deployed", "Commando Attack +240%");

    private OfficerModal aTornado = new OfficerModal(2, "Tornado (A-class)", "Deployment");
    private SkillModal munitionsInterception = new SkillModal(2, "Munitions Interception",  "Enemy Unit Attack -75%. This effect doubled while on another battlefield. Takes effect when deployed.", "Enemy Unit Attack -75%-150%");
    private SkillModal blitzkrieg = new SkillModal(2, "Blitzkrieg", "The first 3 marshes each day take 3600 sec less (can’t be reduced to less than 10 sec). Takes effect when deployed.", "3600s");
    private SkillModal expeditionDefense = new SkillModal(2, "Expedition Defense", "Unit Defense +120% This effect doubles while on another battlefield. Takes effect when deployed.", "Unit Defense 120%-240%");

    private OfficerModal sTankman = new OfficerModal(3, "Tankman (S-class", "Deployment");
    private SkillModal vehicleDefense = new SkillModal(3, "Vehicle Defense", "Vehicle Defense +300%. Takes effect when deployed", "Vehicle Defense 300%");
    private SkillModal vehicleAttack = new SkillModal(3, "Vehicle Attack", "Vehicle Attack +300%. Takes effect when deployed", "Vehicle Attack 300%");
    private SkillModal vehicleHp = new SkillModal(3, "Vehicle HP", "Vehicle HP +300%. Takes effect when deployed", "Vehicle HP 300%");

    private OfficerModal sEric = new OfficerModal(4, "Eric (S-class)", "Technology");
    private SkillModal resourceProtection = new SkillModal(4, "Resource Protection", "Max resources protected +600%.Takes effect when assigned to the Technology Department.", "Protected resources 600%");
    private SkillModal upkeepEfficiency = new SkillModal(4, "Upkeep efficiency", "Unit upkeep efficiency +120%. Takes effect when assigned to the Technology Department", "Upkeep efficiency 120%");
    private SkillModal researchInvestment = new SkillModal(4, "Research investment", "Technology Research speed +210%. Takes effect when assigned to the Technology Department", "Research speed 210%");

    private OfficerModal sCptGlory = new OfficerModal(5, "Captain Glory (S-class)", "Defense");
    private SkillModal guardiansResolve = new SkillModal(5, "Guardians Resolve", "Unit HP +240% when defending the Command Center. Takes effect when assigned to the Defense Department.", "Unit HP 240%");
    private SkillModal defenseElite = new SkillModal(5, "Defense Elite", "Unit Defense +240% when defending the Command Center. Takes effect when assigned to the Defense Department.", "Unit Defense 240%");
    private SkillModal offensiveDefense = new SkillModal(5, "Offensive Defense", "Unit Attack +240% when defending the Command Center. Takes effect when assigned to the Defense Department.", "Unit Attack 240%");

    private OfficerModal bMartin = new OfficerModal(6, "Martin (B-Class)", "Logistics");
    private SkillModal paintProduction = new SkillModal(6, "Paint Production", "Paint Production +480%. Takes effect when assigned to the Logistics Department.", "Paint Production 480%");
    private SkillModal firstAid = new SkillModal(6, "First Aid", "Healing speed +180%. Takes effect when assigned to the Logistics Department.","Healing speed 180%");
    private SkillModal economist = new SkillModal(6, "Economist", "Takes effect when assigned to the Logistics Department.", "Paint Production 480%");

    private OfficerModal bStark = new OfficerModal(7, "Stark (B-class)", "Military");
    private SkillModal unitRecruitment = new SkillModal(7, "Unit Recruitment", "Unit training capacity +30000. Takes effect when assigned to the Military Department.", "Unit training capacity 30000");
    private SkillModal artilleryTraining = new SkillModal(7, "Artillery Training", "Artillery training speed +180%. Takes effect when assignned to the Military Department.", "Artillery training speed 180%");
    private SkillModal copterTraining = new SkillModal(7, "Copter Training", "Copter training speed +180%. Takes effect when assignned to the Military Department.", "Copter training speed 180%");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        filter = findViewById(R.id.officer_type_filter);
        infoOfficerA = findViewById(R.id.officerA_info);
        infoOfficerB = findViewById(R.id.officerB_info);
        officerA = findViewById(R.id.officerA_select);
        officerB = findViewById(R.id.officerB_select);
        summaryA = findViewById(R.id.summaryA);
        summaryB = findViewById(R.id.summaryB);
        dbHelper = new DBHelper(CompareActivity.this);

        dbHelper.resetDB();
        //reset
        dbHelper.addOfficerToDB(aTornado);
        dbHelper.addSkillToDB(munitionsInterception);
        dbHelper.addSkillToDB(blitzkrieg);
        dbHelper.addSkillToDB(expeditionDefense);

        dbHelper.addOfficerToDB(sVictoria);
        dbHelper.addSkillToDB(commandoHp);
        dbHelper.addSkillToDB(commandoDefense);
        dbHelper.addSkillToDB(commandoAttack);

        dbHelper.addOfficerToDB(sTankman);
        dbHelper.addSkillToDB(vehicleDefense);
        dbHelper.addSkillToDB(vehicleAttack);
        dbHelper.addSkillToDB(vehicleHp);
        //deployment officers
        dbHelper.addOfficerToDB(sEric);
        dbHelper.addSkillToDB(resourceProtection);
        dbHelper.addSkillToDB(upkeepEfficiency);
        dbHelper.addSkillToDB(researchInvestment);
        //tech
        dbHelper.addOfficerToDB(sCptGlory);
        dbHelper.addSkillToDB(guardiansResolve);
        dbHelper.addSkillToDB(defenseElite);
        dbHelper.addSkillToDB(offensiveDefense);
        //defense
        dbHelper.addOfficerToDB(bMartin);
        dbHelper.addSkillToDB(paintProduction);
        dbHelper.addSkillToDB(firstAid);
        dbHelper.addSkillToDB(economist);
        //logistics
        dbHelper.addOfficerToDB(bStark);
        dbHelper.addSkillToDB(unitRecruitment);
        dbHelper.addSkillToDB(artilleryTraining);
        dbHelper.addSkillToDB(copterTraining);
        //military


        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this,
                R.array.officer_types_array, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(filterAdapter);
        filter.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                        switch (item.toString()) {
                            case "Deployment":
                                populateSpinnersAfterTypeSelect("Deployment");
                                break;
                            case "Military":
                                populateSpinnersAfterTypeSelect("Military");
                                break;
                            case "Defense":
                                populateSpinnersAfterTypeSelect("Defense");
                                break;
                            case "Logistics":
                                populateSpinnersAfterTypeSelect("Logistics");
                                break;
                            case "Technology":
                                populateSpinnersAfterTypeSelect("Technology");
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


        officerA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                populateOfficerInfoList(item.toString(), infoOfficerA);
                populateSummaryList(item.toString(), summaryA);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        officerB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                populateOfficerInfoList(item.toString(), infoOfficerB);
                populateSummaryList(item.toString(), summaryB);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void populateSpinnersAfterTypeSelect(String type){
        List<String> emptyArr = new ArrayList<>();
        ArrayAdapter<String> resetAdp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, emptyArr);
        resetAdp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        officerA.setAdapter(resetAdp);
        officerB.setAdapter(resetAdp);
        //reset

        ArrayAdapter<String> adpOfficerA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dbHelper.getOfficerNameListByType(type));
        adpOfficerA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        officerA.setAdapter(adpOfficerA);

        ArrayAdapter<String> adpOfficerB = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dbHelper.getOfficerNameListByType(type));
        adpOfficerB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        officerB.setAdapter(adpOfficerB);
        //populate
    }

    public void populateOfficerInfoList(String name, ListView view){
        List<SkillModal> emptyArr = new ArrayList<>();
        ArrayAdapter resetAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emptyArr);
        resetAdp.setDropDownViewResource(android.R.layout.simple_list_item_1);
        view.setAdapter(resetAdp);
        //reset

        ArrayAdapter<SkillModal> skillAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbHelper.getSkillSet(name));
        skillAdp.setDropDownViewResource(android.R.layout.simple_list_item_1);
        view.setAdapter(skillAdp);
        //populate

    }

    private void populateSummaryList(String name, ListView view) {
        List<SkillModal> emptyArr = new ArrayList<>();
        ArrayAdapter resetAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emptyArr);
        resetAdp.setDropDownViewResource(android.R.layout.simple_list_item_1);
        view.setAdapter(resetAdp);
        //reset

        List<String> effectList = dbHelper.getSkillEffects(name);
        ArrayAdapter effectAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, effectList);
        resetAdp.setDropDownViewResource(android.R.layout.simple_list_item_1);
        view.setAdapter(effectAdp);
        //populate
    }



}
