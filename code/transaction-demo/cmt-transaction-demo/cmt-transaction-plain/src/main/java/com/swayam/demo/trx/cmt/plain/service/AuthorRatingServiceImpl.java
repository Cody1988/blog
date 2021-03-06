package com.swayam.demo.trx.cmt.plain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swayam.demo.trx.cmt.plain.dao.AuthorDao;
import com.swayam.demo.trx.cmt.plain.dao.RatingDao;
import com.swayam.demo.trx.cmt.plain.entity.Author;
import com.swayam.demo.trx.cmt.plain.entity.Rating;
import com.swayam.demo.trx.cmt.plain.web.dto.AuthorRatingRequest;

public class AuthorRatingServiceImpl implements AuthorRatingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRatingServiceImpl.class);

	private final RatingDao ratingDao;
	private final AuthorDao authorDao;

	public AuthorRatingServiceImpl(RatingDao ratingDao, AuthorDao authorDao) {
		this.ratingDao = ratingDao;
		this.authorDao = authorDao;
	}

	@Override
	public List<Rating> getRatings() {
		return ratingDao.getRatings();
	}

	@Override
	public Map<String, Long> addAuthorRating(AuthorRatingRequest authorRatingRequest) {
		long ratingId = ratingDao.addRating(new Rating(null, authorRatingRequest.getUserName(),
				authorRatingRequest.getAuthorId(), authorRatingRequest.getRating()));

		LOGGER.info("saved ratingId: {}", ratingId);

		long authorId = authorDao.addAuthor(new Author(authorRatingRequest.getAuthorId(),
				authorRatingRequest.getAuthorFirstName(), authorRatingRequest.getAuthorLastName()));

		LOGGER.info("saved authorId: {}", authorId);

		Map<String, Long> map = new HashMap<>();
		map.put("ratingId", ratingId);
		map.put("authorId", authorId);
		return map;
	}

}
