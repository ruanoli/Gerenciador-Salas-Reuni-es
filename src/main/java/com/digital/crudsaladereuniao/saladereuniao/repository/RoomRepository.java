package com.digital.crudsaladereuniao.saladereuniao.repository;

import com.digital.crudsaladereuniao.saladereuniao.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository <Room,Long>{

}
