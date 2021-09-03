package com.digital.crudsaladereuniao.saladereuniao.controller;

import com.digital.crudsaladereuniao.saladereuniao.exception.ResourceNotFoundException;
import com.digital.crudsaladereuniao.saladereuniao.model.Room;
import com.digital.crudsaladereuniao.saladereuniao.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.validation.Valid;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    public RoomController() throws ResourceNotFoundException {
    }

    @GetMapping("/rooms/{id}")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId)
        throws ResourceNotFoundException{
            Room room  = roomRepository.findById(roomId)
                    .orElseThrow(()-> new ResourceNotFoundException("Room Not Found "+ roomId));
            return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom (@Validated @RequestBody Room room){
        return RoomRepository.save(room);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value= "id") Long roomId,
                                           @Validated @RequestBody Room roomDatails) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room Not Found this id: " + roomId));
        room.setName(roomDatails.getName());
        room.setDate(roomDatails.getDate());
        room.setStartHour(roomDatails.getStartHour());
        room.setEndHour(roomDatails.getEndHour());
        final Room updateRoom = RoomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
    }
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room Not fpund for this id "+ roomId));

        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

