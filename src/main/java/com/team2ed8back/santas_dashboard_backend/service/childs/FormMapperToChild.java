package com.team2ed8back.santas_dashboard_backend.service.childs;

import com.team2ed8back.santas_dashboard_backend.entity.childs.Child;
import io.vavr.control.Either;

public class FormMapperToChild {

    public static Either<String, Child> formUpdateMapperToChild(Child childToUpdate, FormUpdateBehavior formUpdateBehavior){
        if( formUpdateBehavior.kindness()<0 || formUpdateBehavior.kindness() > 10){
            return Either.left("Value, kindness is invalid, Valid values are integers between 0 and 10");
        }
        if(formUpdateBehavior.respectful() < 0 || formUpdateBehavior.respectful() > 10){
            return Either.left("Value, respectful is invalid, Valid values are integers between 0 and 10");
        }
        if(formUpdateBehavior.patience() < 0 || formUpdateBehavior.patience() > 10){
            return Either.left("Value, patience is invalid, Valid values are integers between 0 and 10");
        }
        if(formUpdateBehavior.effort() < 0 || formUpdateBehavior.effort() > 10){
            return Either.left("Value, effort is invalid, Valid values are integers between 0 and 10");
        }
        if(formUpdateBehavior.teamWork() < 0 || formUpdateBehavior.teamWork() > 10){
            return Either.left("Value, team work is invalid, Valid values are integers between 0 and 10");
        }
        childToUpdate.setFemale(formUpdateBehavior.isFemale());
        childToUpdate.setLinkImgProfile(ChildService.generateLinkImgProfile(generateLink(formUpdateBehavior.isFemale(), childToUpdate.getAge())));
        childToUpdate.setKindnessLevel(formUpdateBehavior.kindness());
        childToUpdate.setRespectfulnessLevel(formUpdateBehavior.respectful());
        childToUpdate.setPatienceLevel(formUpdateBehavior.patience());
        childToUpdate.setEffortLevel(formUpdateBehavior.effort());
        childToUpdate.setTeamworkLevel(formUpdateBehavior.teamWork());
        childToUpdate.setBehaviorLevel();
        System.out.println(childToUpdate.getBehaviorLevel());
        return Either.right(childToUpdate);
    }

    public static Either<String, Child> FormSaveChildToChild(FormSaveChild formSaveChild){
        if(formSaveChild.name() == null || formSaveChild.name().isEmpty()){
            return Either.left("Name is required");
        }
        if(formSaveChild.age()<1 || formSaveChild.age() > 110){
            return Either.left("Age is invalid, Valid values are integers between 0 and 110");
        }

        if(formSaveChild.kindness()<0 || formSaveChild.kindness() > 10){
            return Either.left("Value, kindness is invalid, Valid values are integers between 0 and 10");
        }
        if(formSaveChild.respectful() < 0 || formSaveChild.respectful() > 10){
            return Either.left("Value, respectful is invalid, Valid values are integers between 0 and 10");
        }
        if(formSaveChild.patience() < 0 || formSaveChild.patience() > 10){
            return Either.left("Value, patience is invalid, Valid values are integers between 0 and 10");
        }
        if(formSaveChild.effort() < 0 || formSaveChild.effort() > 10){
            return Either.left("Value, effort is invalid, Valid values are integers between 0 and 10");
        }
        if(formSaveChild.teamWork() < 0 || formSaveChild.teamWork() > 10){
            return Either.left("Value, team work is invalid, Valid values are integers between 0 and 10");
        }

        return Either.right(Child.builder()
                .name(formSaveChild.name())
                .age(formSaveChild.age())
                .isFemale(formSaveChild.isFemale())
                .linkImgProfile(ChildService.generateLinkImgProfile(generateLink(formSaveChild.isFemale(), formSaveChild.age())))
                .kindnessLevel(formSaveChild.kindness())
                .respectfulnessLevel(formSaveChild.respectful())
                .patienceLevel(formSaveChild.patience())
                .effortLevel(formSaveChild.effort())
                .teamworkLevel(formSaveChild.teamWork())
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

}
