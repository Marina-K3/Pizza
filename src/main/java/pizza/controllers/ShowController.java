package pizza.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import pizza.dto.ProductDTO;
import pizza.dto.ShowDTO;

import java.util.List;

public class ShowController {
//    public ResponseEntity<List<ProductDTO>> showProducts(@RequestBody @Valid ShowDTO showDTO, BindingResult bindingResult){
////        if (bindingResult == null ) {
////            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////        }
////        if (bindingResult.hasErrors())
////            throw new ValidationException(bindingResult);
////        showDTOValidator.validate(showDTO,bindingResult);
////        Pageable pageable = productService.getPageable(showDTO);
////        List<ProductDTO> productList =  productService.findAllCertainProductsAndDisplayCertainCount(showDTO.getFrom(), pageable).stream().map(this::convertToProductDTO).toList();
////
////
//        return new ResponseEntity<>(productList, HttpStatus.OK);
//    }
}
