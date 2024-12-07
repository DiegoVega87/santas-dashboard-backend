package com.team2ed8back.santas_dashboard_backend.service.childs;

import com.team2ed8back.santas_dashboard_backend.entity.childs.Child;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;

public class FormMapperToChild {

    public static Either<String, Child> formUpdateMapperToChild(Child childToUpdate, FormUpdateBehavior formUpdateBehavior){
        if( formUpdateBehavior.bondad()<0 || formUpdateBehavior.bondad() > 5){
            return Either.left("Value, bondad is invalid, Valid values are integers between 0 and 5");
        }
        if(formUpdateBehavior.respeto() < 0 || formUpdateBehavior.respeto() > 5){
            return Either.left("Value, respecto is invalid, Valid values are integers between 0 and 5");
        }
        if(formUpdateBehavior.paciencia() < 0 || formUpdateBehavior.paciencia() > 5){
            return Either.left("Value, paciencia is invalid, Valid values are integers between 0 and 5");
        }
        if(formUpdateBehavior.esfuerzo() < 0 || formUpdateBehavior.esfuerzo() > 5){
            return Either.left("Value, esfuerzo is invalid, Valid values are integers between 0 and 5");
        }
        if(formUpdateBehavior.trabajoEnEquipo() < 0 || formUpdateBehavior.trabajoEnEquipo() > 5){
            return Either.left("Value, team work is invalid, Valid values are integers between 0 and 5");
        }
        childToUpdate.setKindnessLevel(formUpdateBehavior.bondad());
        childToUpdate.setRespectfulnessLevel(formUpdateBehavior.respeto());
        childToUpdate.setPatienceLevel(formUpdateBehavior.paciencia());
        childToUpdate.setEffortLevel(formUpdateBehavior.esfuerzo());
        childToUpdate.setTeamworkLevel(formUpdateBehavior.trabajoEnEquipo());
        childToUpdate.setBehaviorLevel();
        System.out.println(childToUpdate.getBehaviorLevel());
        return Either.right(childToUpdate);
    }

    public static Either<String, Child> FormSaveChildToChild(FormSaveChild formSaveChild){
        if(formSaveChild.name() == null || formSaveChild.name().isEmpty()){
            return Either.left("Name is required");
        }
        if(formSaveChild.bondad()<0 || formSaveChild.bondad() > 5){
            return Either.left("Value, bondad is invalid, Valid values are integers between 0 and 5");
        }
        if(formSaveChild.respeto() < 0 || formSaveChild.respeto() > 5){
            return Either.left("Value, respeto is invalid, Valid values are integers between 0 and 5");
        }
        if(formSaveChild.paciencia() < 0 || formSaveChild.paciencia() > 5){
            return Either.left("Value, paciencia is invalid, Valid values are integers between 0 and 5");
        }
        if(formSaveChild.esfuerzo() < 0 || formSaveChild.esfuerzo() > 5){
            return Either.left("Value, esfuerzo is invalid, Valid values are integers between 0 and 5");
        }
        if(formSaveChild.trabajoEnEquipo() < 0 || formSaveChild.trabajoEnEquipo() > 5){
            return Either.left("Value, trabajo en equipo is invalid, Valid values are integers between 0 and 5");
        }

        return Either.right(Child.builder()
                .name(formSaveChild.name())
                .kindnessLevel(formSaveChild.bondad())
                .respectfulnessLevel(formSaveChild.respeto())
                .patienceLevel(formSaveChild.paciencia())
                .effortLevel(formSaveChild.esfuerzo())
                .teamworkLevel(formSaveChild.trabajoEnEquipo())
                .build());
    }

    private static String generateLink(boolean isFemale, int age) {
        StringBuilder link = new StringBuilder();
        if(age<18) {
            link.append("child");
            if(isFemale) {
                link.append("Female");
            }else{
                link.append("Boy");
            }
        }else{
            link.append("adult");
            if(isFemale) {
                link.append("Female");
            }else{
                link.append("Man");
            }
        }
        return link.toString();
    }

    //
    public static List<Behavior> childToBehaviorLevel(Child child){
        List<Behavior> behaviorLevel = new ArrayList<>();
        Behavior bondad = new Behavior("bondad", child.getKindnessLevel());
        Behavior respeto = new Behavior("respeto", child.getRespectfulnessLevel());
        Behavior paciencia = new Behavior("paciencia", child.getPatienceLevel());
        Behavior esfuerzo = new Behavior("esfuerzo", child.getEffortLevel());
        Behavior trabajoEnEquipo = new Behavior("trabajoEnEquipo", child.getTeamworkLevel());

        behaviorLevel.add(bondad);
        behaviorLevel.add(respeto);
        behaviorLevel.add(paciencia);
        behaviorLevel.add(esfuerzo);
        behaviorLevel.add(trabajoEnEquipo);
        return behaviorLevel;
    }

    public static ChildsResponseDto childToChildResponseDto(Child child){
        return ChildsResponseDto.builder()
                .name(child.getName())
                .characteristics(childToBehaviorLevel(child))
                .classification(String.valueOf(child.getBehaviorLevel()).replaceAll("_"," ").split(" ")[0].toLowerCase())
                .build();
    }

}
