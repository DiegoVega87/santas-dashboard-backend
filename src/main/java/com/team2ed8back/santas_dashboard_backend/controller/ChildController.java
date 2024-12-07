package com.team2ed8back.santas_dashboard_backend.controller;


import com.team2ed8back.santas_dashboard_backend.entity.childs.Child;
import com.team2ed8back.santas_dashboard_backend.service.childs.ChildService;
import com.team2ed8back.santas_dashboard_backend.service.childs.ChildsResponseDto;
import com.team2ed8back.santas_dashboard_backend.service.childs.FormSaveChild;
import com.team2ed8back.santas_dashboard_backend.service.childs.FormUpdateBehavior;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/childs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChildController {

    private final ChildService childService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        System.out.println(childService.listChildIsEmpty());
        return ResponseEntity.ok(childService.findAll());
    }

    @GetMapping("/sort-by/available-to-gift")
    public ResponseEntity<?> findBySortByAvailableToGift() {
        return ResponseEntity.ok(childService.sortByAvailableToGift());
    }

    @GetMapping("/sort-by/unavailable-to-gift")
    public ResponseEntity<?> findBySortByNotAvailableToGift() {
        return ResponseEntity.ok(childService.sortByNotAvailableToGift());
    }

    @PostMapping
    public ResponseEntity<?> saveChild(@RequestBody FormSaveChild formSaveChild) {
        Either<String, ChildsResponseDto> child = childService.saveChild(formSaveChild);
        if(child.isRight()) {
            return ResponseEntity.ok().body(child.get());
        }
        return ResponseEntity.badRequest().body(child.getLeft());
    }

    @PutMapping
    public ResponseEntity<?> updateValues(@RequestBody FormUpdateBehavior formUpdateBehavior) {
        Either<String, ChildsResponseDto> resutl = childService.updateBehaviorChild(formUpdateBehavior);

        if(resutl.isRight()) {
            return ResponseEntity.ok(resutl.get());
        }
        return ResponseEntity.badRequest().body(resutl.getLeft());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Either<String, ChildsResponseDto> child = childService.findChildById(id);
        if(child.isRight()) {
            return ResponseEntity.ok().body(child.get());
        }
        return ResponseEntity.ok(child.getLeft());
    }

    @DeleteMapping
    public void deleteAllChilds() {
        childService.delete();
    }
}
