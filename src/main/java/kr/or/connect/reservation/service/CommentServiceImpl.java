package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.ReservationUserCommentImageDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDao commentDao;
	@Autowired
	ReservationUserCommentImageDao reservationUserCommentImageDao;
	
	@Override
	@Transactional
	public List<Comment> getComments(int num) {
		List<Comment> cl = commentDao.selectComments(num);
	
		for(Comment c : cl) {
			c.setReservationUserCommentImage(reservationUserCommentImageDao.selectCommentImages(c.getId()));
		}
		return cl;
	}

	@Override
	public double getAvgscore(int num) {
		return commentDao.selectAvgscore(num);
	}

}
