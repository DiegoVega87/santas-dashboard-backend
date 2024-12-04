package com.team2ed8back.santas_dashboard_backend.service.childs;

import com.team2ed8back.santas_dashboard_backend.entity.childs.Child;
import com.team2ed8back.santas_dashboard_backend.entity.childs.ChildRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;

    public List<Child> findAll(){
        return childRepository.findAll();
    }

    public List<Child> sortByAvailableToGift(){
        return childRepository.findAllBySuitableForGiftTrue();
    }

    public List<Child> sortByNotAvailableToGift(){
        return childRepository.findAllBySuitableForGiftFalse();
    }

    public boolean listChildIsEmpty(){
        return childRepository.findAll().isEmpty();
    }

    public Either<String, Child> updateBehaviorChild(FormUpdateBehavior formUpdateBehavior) {
        Child updateChild = childRepository.findById(formUpdateBehavior.idChild())
                .orElse(null);

        if (updateChild == null) {
            return Either.left("Child with id " + formUpdateBehavior.idChild() + " not found");
        }

        Either<String, Child> mappedChild = FormMapperToChild.formUpdateMapperToChild(updateChild, formUpdateBehavior);

        if (mappedChild.isLeft()) {
            return Either.left(mappedChild.getLeft());
        }

        Child savedChild = childRepository.save(mappedChild.get());

        return Either.right(savedChild);
    }

    public Either<String, Child> findChildById(Integer id){
        Child child = childRepository.findById(id).orElse(null);
        if(child == null){
            return Either.left(new IllegalArgumentException("Child with id " + id + " not found").getMessage());
        }
        else{
            return Either.right(child);
        }
    }

    public Either<String, Child> saveChild(FormSaveChild formSaveChild){
        Either<String, Child> child = FormMapperToChild.FormSaveChildToChild(formSaveChild);
        if(child.isLeft()){
            return Either.left(child.getLeft());
        }
        childRepository.save(child.get());
        return Either.right(child.get());
    }

    public void saveChildList() {
        Child child1 = Child.builder().name("Juan Camilo").age(12).isFemale(false).linkImgProfile(generateLinkImgProfile("childBoy"))
                .kindnessLevel(10).respectfulnessLevel(7).patienceLevel(7)
                .effortLevel(10).teamworkLevel(10).build();

        Child child2 = Child.builder().name("Ana Teres").age(10).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(3).respectfulnessLevel(4).patienceLevel(3)
                .effortLevel(2).teamworkLevel(4).build();

        Child child3 = Child.builder().name("María Carmen").age(8).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(4).respectfulnessLevel(3).patienceLevel(4)
                .effortLevel(4).teamworkLevel(3).build();

        Child child4 = Child.builder().name("Sofia Isabel").age(9).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(3).respectfulnessLevel(2).patienceLevel(3)
                .effortLevel(3).teamworkLevel(4).build();

        Child child5 = Child.builder().name("Luis Angel").age(11).isFemale(false).linkImgProfile(generateLinkImgProfile("childBoy"))
                .kindnessLevel(4).respectfulnessLevel(4).patienceLevel(3)
                .effortLevel(5).teamworkLevel(4).build();

        Child child6 = Child.builder().name("Sofía Dolores").age(7).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(5).respectfulnessLevel(5).patienceLevel(4)
                .effortLevel(4).teamworkLevel(5).build();

        Child child7 = Child.builder().name("Pedro Placencia").age(6).isFemale(false).linkImgProfile(generateLinkImgProfile("adultMan"))
                .kindnessLevel(2).respectfulnessLevel(2).patienceLevel(2)
                .effortLevel(2).teamworkLevel(3).build();

        Child child8 = Child.builder().name("Isabella Jose").age(13).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(5).respectfulnessLevel(5).patienceLevel(5)
                .effortLevel(5).teamworkLevel(5).build();

        Child child9 = Child.builder().name("Diego Mendoza").age(24).isFemale(false).linkImgProfile(generateLinkImgProfile("adultMan"))
                .kindnessLevel(3).respectfulnessLevel(3).patienceLevel(3)
                .effortLevel(3).teamworkLevel(2).build();

        Child child10 = Child.builder().name("Lucía Martinez").age(9).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(4).respectfulnessLevel(4).patienceLevel(3)
                .effortLevel(4).teamworkLevel(4).build();

        Child child11 = Child.builder().name("Marta Angel").age(10).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(3).respectfulnessLevel(4).patienceLevel(2)
                .effortLevel(4).teamworkLevel(3).build();

        Child child12 = Child.builder().name("Carmen Josefa").age(8).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(3).respectfulnessLevel(3).patienceLevel(3)
                .effortLevel(3).teamworkLevel(4).build();

        Child child13 = Child.builder().name("Fernando Ruiz").age(7).isFemale(false).linkImgProfile(generateLinkImgProfile("childBoy"))
                .kindnessLevel(4).respectfulnessLevel(4).patienceLevel(4)
                .effortLevel(5).teamworkLevel(4).build();

        Child child14 = Child.builder().name("Valeria Romero").age(6).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(2).respectfulnessLevel(2).patienceLevel(3)
                .effortLevel(2).teamworkLevel(2).build();

        Child child15 = Child.builder().name("Javier Alonso").age(11).isFemale(false).linkImgProfile(generateLinkImgProfile("childBoy"))
                .kindnessLevel(5).respectfulnessLevel(4).patienceLevel(4)
                .effortLevel(3).teamworkLevel(4).build();

        Child child16 = Child.builder().name("Paula Muñoz").age(12).isFemale(true).linkImgProfile(generateLinkImgProfile("childFemale"))
                .kindnessLevel(3).respectfulnessLevel(3).patienceLevel(2)
                .effortLevel(3).teamworkLevel(3).build();

        List<Child> children = List.of(child1,child2,child3,
                child4,child5,child6,child7,child8,child9,child10,
                child11,child12,child13,child14,child15);

        if(listChildIsEmpty()){
            childRepository.saveAll(children);
        }
    }

    public static String generateLinkImgProfile(String type) {
        return switch (type){
            case "childBoy" -> "https://img.freepik.com/vector-gratis/chico-alegre-expresion-neutra-sombrero-navidad_1308-157364.jpg";
            case "childFemale" -> "https://img.freepik.com/vector-gratis/nina-elfa-festiva-traje-navideno_1308-162841.jpg";
            case "adultMan" -> "https://img.freepik.com/vector-premium/elegante-hombre-negocios-disfraz-papa-noel-caja-regalos_1023984-54023.jpg";
            case "adultFemale" -> "https://img.freepik.com/fotos-premium/mujer-navidad-icono-sueter-navidad_962764-260676.jpg";
            default -> "type is invalid";
        };
    }
}