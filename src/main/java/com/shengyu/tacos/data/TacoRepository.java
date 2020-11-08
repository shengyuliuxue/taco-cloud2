package com.shengyu.tacos.data;


import com.shengyu.tacos.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
//    Taco save(Taco design);
}
