package com.libarymanagementsystem.project1.core.utiles;

import com.libarymanagementsystem.project1.core.result.Result;
import com.libarymanagementsystem.project1.core.result.ResultData;
import com.libarymanagementsystem.project1.dto.response.Book.BookResponse;
import com.libarymanagementsystem.project1.dto.response.CursorResponse;
import org.springframework.data.domain.Page;

public class CreateResult {
    public static <T>ResultData<T> created(T data){
        return new ResultData<>(true,Msg.CREATED,"201",data);
    }

    public static <T>ResultData<T> validateError(T data){
        return new ResultData<>(false,Msg.VALIDATE_ERROR,"400",data);
    }

    public static <T>ResultData<T> success(T data){
        return new ResultData<>(true,Msg.SUCCESS,"209",data);
    }

    public static Result notFoundError(String msg){
        return new Result(false,msg,"404");
    }
    public static <T >ResultData<CursorResponse<T>>cursor(Page<T>pageData){

        CursorResponse<T>cursorResponse=new CursorResponse<>();
        cursorResponse.setItems(pageData.getContent());
        cursorResponse.setPageSize(pageData.getSize());
        cursorResponse.setPageNumber(pageData.getNumber());
        cursorResponse.setTotalElements(pageData.getTotalElements());

        return CreateResult.success(cursorResponse);
    }
    public static<T> ResultData<T> deleteResult(T data){
        return new ResultData<>(true,Msg.DELETE,"200",data);
    }
}
