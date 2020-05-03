package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationDetailInfo;
import kr.or.connect.reservation.dto.ReservationParam;

public interface ReservationService {
	public boolean isThereEmail(String email);
	public int[] getResCount(String email);
	public List<ReservationDetailInfo> getResInfo(String email);
	public void reserve(ReservationParam reservationInfo);
	public void cancelReservation(int id);
}
