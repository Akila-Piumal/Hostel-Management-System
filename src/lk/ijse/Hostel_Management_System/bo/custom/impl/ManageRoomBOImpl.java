package lk.ijse.Hostel_Management_System.bo.custom.impl;

import lk.ijse.Hostel_Management_System.bo.custom.ManageRoomBO;
import lk.ijse.Hostel_Management_System.dao.DAOFactory;
import lk.ijse.Hostel_Management_System.dao.SuperDAO;
import lk.ijse.Hostel_Management_System.dao.custom.RoomDAO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class ManageRoomBOImpl implements ManageRoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> all = roomDAO.getAll();
        List<RoomDTO> allRooms=new ArrayList<>();
        for (Room room : all) {
            allRooms.add(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return allRooms;
    }

    @Override
    public boolean updateQty(String roomTypeID, int qty) {
        return roomDAO.updateQty(roomTypeID,qty);
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        return roomDAO.save(new Room(roomDTO.getRoomTypeId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty()));
    }
}
