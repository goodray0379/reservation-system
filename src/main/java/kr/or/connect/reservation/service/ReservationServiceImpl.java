package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.ReservationDetailInfo;
import kr.or.connect.reservation.dto.ReservationParam;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationDao reservationDao;
	
	@Override
	public boolean isThereEmail(String email) {
		if(reservationDao.SelectReservationCount(email)!=0)
			return true;
		else
			return false;
	}
	
	@Override
	@Transactional
	public int[] getResCount(String email) {
		int[] count = new int[2];
		count[0] = reservationDao.SelectReservationCount(email);
		count[1] = reservationDao.SelectCancelCount(email);
		return count;
	}

	@Override
	@Transactional
	public List<ReservationDetailInfo> getResInfo(String email) {
		List<ReservationDetailInfo> list = reservationDao.selectReservationInfos(email);
		for(ReservationDetailInfo ri : list) {
			ri.setSumPrice(reservationDao.selectSumPrice(ri.getId()));
		}
		return list;
	}
	
	@Override
	public void reserve(ReservationParam reservationInfo) {
		Long id = reservationDao.insertReservationInfo(reservationInfo);
		for(int i=0; i<reservationInfo.getPrices().size(); i++) {
			reservationDao.insertReservationPrice(id, reservationInfo.getPrices().get(i).getProductPriceId(), reservationInfo.getPrices().get(i).getCount());
		}
	}

	@Override
	public void cancelReservation(int id) {
		reservationDao.updateReservationInfo(id);
	}
}
