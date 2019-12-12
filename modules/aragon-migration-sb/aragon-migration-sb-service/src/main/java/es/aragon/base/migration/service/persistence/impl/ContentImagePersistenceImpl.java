/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package es.aragon.base.migration.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import es.aragon.base.migration.exception.NoSuchContentImageException;
import es.aragon.base.migration.model.ContentImage;
import es.aragon.base.migration.model.impl.ContentImageImpl;
import es.aragon.base.migration.model.impl.ContentImageModelImpl;
import es.aragon.base.migration.service.persistence.ContentImagePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the content image service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentImagePersistence
 * @see es.aragon.base.migration.service.persistence.ContentImageUtil
 * @generated
 */
@ProviderType
public class ContentImagePersistenceImpl extends BasePersistenceImpl<ContentImage>
	implements ContentImagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContentImageUtil} to access the content image persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContentImageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, ContentImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, ContentImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, ContentImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, ContentImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ContentImageModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the content images where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching content images
	 */
	@Override
	public List<ContentImage> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content images where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @return the range of matching content images
	 */
	@Override
	public List<ContentImage> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the content images where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching content images
	 */
	@Override
	public List<ContentImage> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentImage> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content images where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching content images
	 */
	@Override
	public List<ContentImage> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<ContentImage> list = null;

		if (retrieveFromCache) {
			list = (List<ContentImage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContentImage contentImage : list) {
					if (!Objects.equals(uuid, contentImage.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CONTENTIMAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContentImageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<ContentImage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentImage>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first content image in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content image
	 * @throws NoSuchContentImageException if a matching content image could not be found
	 */
	@Override
	public ContentImage findByUuid_First(String uuid,
		OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException {
		ContentImage contentImage = fetchByUuid_First(uuid, orderByComparator);

		if (contentImage != null) {
			return contentImage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentImageException(msg.toString());
	}

	/**
	 * Returns the first content image in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content image, or <code>null</code> if a matching content image could not be found
	 */
	@Override
	public ContentImage fetchByUuid_First(String uuid,
		OrderByComparator<ContentImage> orderByComparator) {
		List<ContentImage> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content image in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content image
	 * @throws NoSuchContentImageException if a matching content image could not be found
	 */
	@Override
	public ContentImage findByUuid_Last(String uuid,
		OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException {
		ContentImage contentImage = fetchByUuid_Last(uuid, orderByComparator);

		if (contentImage != null) {
			return contentImage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentImageException(msg.toString());
	}

	/**
	 * Returns the last content image in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content image, or <code>null</code> if a matching content image could not be found
	 */
	@Override
	public ContentImage fetchByUuid_Last(String uuid,
		OrderByComparator<ContentImage> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ContentImage> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the content images before and after the current content image in the ordered set where uuid = &#63;.
	 *
	 * @param contentImageId the primary key of the current content image
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content image
	 * @throws NoSuchContentImageException if a content image with the primary key could not be found
	 */
	@Override
	public ContentImage[] findByUuid_PrevAndNext(long contentImageId,
		String uuid, OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException {
		ContentImage contentImage = findByPrimaryKey(contentImageId);

		Session session = null;

		try {
			session = openSession();

			ContentImage[] array = new ContentImageImpl[3];

			array[0] = getByUuid_PrevAndNext(session, contentImage, uuid,
					orderByComparator, true);

			array[1] = contentImage;

			array[2] = getByUuid_PrevAndNext(session, contentImage, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContentImage getByUuid_PrevAndNext(Session session,
		ContentImage contentImage, String uuid,
		OrderByComparator<ContentImage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENTIMAGE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ContentImageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contentImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContentImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the content images where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ContentImage contentImage : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(contentImage);
		}
	}

	/**
	 * Returns the number of content images where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching content images
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENTIMAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "contentImage.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "contentImage.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(contentImage.uuid IS NULL OR contentImage.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTORIGINID =
		new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, ContentImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContentOriginId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTORIGINID =
		new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, ContentImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContentOriginId",
			new String[] { Long.class.getName() },
			ContentImageModelImpl.CONTENTORIGINID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTORIGINID = new FinderPath(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByContentOriginId", new String[] { Long.class.getName() });

	/**
	 * Returns all the content images where contentOriginId = &#63;.
	 *
	 * @param contentOriginId the content origin ID
	 * @return the matching content images
	 */
	@Override
	public List<ContentImage> findByContentOriginId(long contentOriginId) {
		return findByContentOriginId(contentOriginId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content images where contentOriginId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentOriginId the content origin ID
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @return the range of matching content images
	 */
	@Override
	public List<ContentImage> findByContentOriginId(long contentOriginId,
		int start, int end) {
		return findByContentOriginId(contentOriginId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the content images where contentOriginId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentOriginId the content origin ID
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching content images
	 */
	@Override
	public List<ContentImage> findByContentOriginId(long contentOriginId,
		int start, int end, OrderByComparator<ContentImage> orderByComparator) {
		return findByContentOriginId(contentOriginId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content images where contentOriginId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentOriginId the content origin ID
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching content images
	 */
	@Override
	public List<ContentImage> findByContentOriginId(long contentOriginId,
		int start, int end, OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTORIGINID;
			finderArgs = new Object[] { contentOriginId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTORIGINID;
			finderArgs = new Object[] {
					contentOriginId,
					
					start, end, orderByComparator
				};
		}

		List<ContentImage> list = null;

		if (retrieveFromCache) {
			list = (List<ContentImage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContentImage contentImage : list) {
					if ((contentOriginId != contentImage.getContentOriginId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CONTENTIMAGE_WHERE);

			query.append(_FINDER_COLUMN_CONTENTORIGINID_CONTENTORIGINID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContentImageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentOriginId);

				if (!pagination) {
					list = (List<ContentImage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentImage>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first content image in the ordered set where contentOriginId = &#63;.
	 *
	 * @param contentOriginId the content origin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content image
	 * @throws NoSuchContentImageException if a matching content image could not be found
	 */
	@Override
	public ContentImage findByContentOriginId_First(long contentOriginId,
		OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException {
		ContentImage contentImage = fetchByContentOriginId_First(contentOriginId,
				orderByComparator);

		if (contentImage != null) {
			return contentImage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentOriginId=");
		msg.append(contentOriginId);

		msg.append("}");

		throw new NoSuchContentImageException(msg.toString());
	}

	/**
	 * Returns the first content image in the ordered set where contentOriginId = &#63;.
	 *
	 * @param contentOriginId the content origin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content image, or <code>null</code> if a matching content image could not be found
	 */
	@Override
	public ContentImage fetchByContentOriginId_First(long contentOriginId,
		OrderByComparator<ContentImage> orderByComparator) {
		List<ContentImage> list = findByContentOriginId(contentOriginId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content image in the ordered set where contentOriginId = &#63;.
	 *
	 * @param contentOriginId the content origin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content image
	 * @throws NoSuchContentImageException if a matching content image could not be found
	 */
	@Override
	public ContentImage findByContentOriginId_Last(long contentOriginId,
		OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException {
		ContentImage contentImage = fetchByContentOriginId_Last(contentOriginId,
				orderByComparator);

		if (contentImage != null) {
			return contentImage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentOriginId=");
		msg.append(contentOriginId);

		msg.append("}");

		throw new NoSuchContentImageException(msg.toString());
	}

	/**
	 * Returns the last content image in the ordered set where contentOriginId = &#63;.
	 *
	 * @param contentOriginId the content origin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content image, or <code>null</code> if a matching content image could not be found
	 */
	@Override
	public ContentImage fetchByContentOriginId_Last(long contentOriginId,
		OrderByComparator<ContentImage> orderByComparator) {
		int count = countByContentOriginId(contentOriginId);

		if (count == 0) {
			return null;
		}

		List<ContentImage> list = findByContentOriginId(contentOriginId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the content images before and after the current content image in the ordered set where contentOriginId = &#63;.
	 *
	 * @param contentImageId the primary key of the current content image
	 * @param contentOriginId the content origin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content image
	 * @throws NoSuchContentImageException if a content image with the primary key could not be found
	 */
	@Override
	public ContentImage[] findByContentOriginId_PrevAndNext(
		long contentImageId, long contentOriginId,
		OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException {
		ContentImage contentImage = findByPrimaryKey(contentImageId);

		Session session = null;

		try {
			session = openSession();

			ContentImage[] array = new ContentImageImpl[3];

			array[0] = getByContentOriginId_PrevAndNext(session, contentImage,
					contentOriginId, orderByComparator, true);

			array[1] = contentImage;

			array[2] = getByContentOriginId_PrevAndNext(session, contentImage,
					contentOriginId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContentImage getByContentOriginId_PrevAndNext(Session session,
		ContentImage contentImage, long contentOriginId,
		OrderByComparator<ContentImage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENTIMAGE_WHERE);

		query.append(_FINDER_COLUMN_CONTENTORIGINID_CONTENTORIGINID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ContentImageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contentOriginId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contentImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContentImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the content images where contentOriginId = &#63; from the database.
	 *
	 * @param contentOriginId the content origin ID
	 */
	@Override
	public void removeByContentOriginId(long contentOriginId) {
		for (ContentImage contentImage : findByContentOriginId(
				contentOriginId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(contentImage);
		}
	}

	/**
	 * Returns the number of content images where contentOriginId = &#63;.
	 *
	 * @param contentOriginId the content origin ID
	 * @return the number of matching content images
	 */
	@Override
	public int countByContentOriginId(long contentOriginId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTENTORIGINID;

		Object[] finderArgs = new Object[] { contentOriginId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENTIMAGE_WHERE);

			query.append(_FINDER_COLUMN_CONTENTORIGINID_CONTENTORIGINID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentOriginId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CONTENTORIGINID_CONTENTORIGINID_2 =
		"contentImage.contentOriginId = ?";

	public ContentImagePersistenceImpl() {
		setModelClass(ContentImage.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the content image in the entity cache if it is enabled.
	 *
	 * @param contentImage the content image
	 */
	@Override
	public void cacheResult(ContentImage contentImage) {
		entityCache.putResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageImpl.class, contentImage.getPrimaryKey(), contentImage);

		contentImage.resetOriginalValues();
	}

	/**
	 * Caches the content images in the entity cache if it is enabled.
	 *
	 * @param contentImages the content images
	 */
	@Override
	public void cacheResult(List<ContentImage> contentImages) {
		for (ContentImage contentImage : contentImages) {
			if (entityCache.getResult(
						ContentImageModelImpl.ENTITY_CACHE_ENABLED,
						ContentImageImpl.class, contentImage.getPrimaryKey()) == null) {
				cacheResult(contentImage);
			}
			else {
				contentImage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all content images.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContentImageImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the content image.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContentImage contentImage) {
		entityCache.removeResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageImpl.class, contentImage.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContentImage> contentImages) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContentImage contentImage : contentImages) {
			entityCache.removeResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
				ContentImageImpl.class, contentImage.getPrimaryKey());
		}
	}

	/**
	 * Creates a new content image with the primary key. Does not add the content image to the database.
	 *
	 * @param contentImageId the primary key for the new content image
	 * @return the new content image
	 */
	@Override
	public ContentImage create(long contentImageId) {
		ContentImage contentImage = new ContentImageImpl();

		contentImage.setNew(true);
		contentImage.setPrimaryKey(contentImageId);

		String uuid = PortalUUIDUtil.generate();

		contentImage.setUuid(uuid);

		return contentImage;
	}

	/**
	 * Removes the content image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentImageId the primary key of the content image
	 * @return the content image that was removed
	 * @throws NoSuchContentImageException if a content image with the primary key could not be found
	 */
	@Override
	public ContentImage remove(long contentImageId)
		throws NoSuchContentImageException {
		return remove((Serializable)contentImageId);
	}

	/**
	 * Removes the content image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the content image
	 * @return the content image that was removed
	 * @throws NoSuchContentImageException if a content image with the primary key could not be found
	 */
	@Override
	public ContentImage remove(Serializable primaryKey)
		throws NoSuchContentImageException {
		Session session = null;

		try {
			session = openSession();

			ContentImage contentImage = (ContentImage)session.get(ContentImageImpl.class,
					primaryKey);

			if (contentImage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContentImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contentImage);
		}
		catch (NoSuchContentImageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ContentImage removeImpl(ContentImage contentImage) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contentImage)) {
				contentImage = (ContentImage)session.get(ContentImageImpl.class,
						contentImage.getPrimaryKeyObj());
			}

			if (contentImage != null) {
				session.delete(contentImage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contentImage != null) {
			clearCache(contentImage);
		}

		return contentImage;
	}

	@Override
	public ContentImage updateImpl(ContentImage contentImage) {
		boolean isNew = contentImage.isNew();

		if (!(contentImage instanceof ContentImageModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(contentImage.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(contentImage);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in contentImage proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ContentImage implementation " +
				contentImage.getClass());
		}

		ContentImageModelImpl contentImageModelImpl = (ContentImageModelImpl)contentImage;

		if (Validator.isNull(contentImage.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			contentImage.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (contentImage.isNew()) {
				session.save(contentImage);

				contentImage.setNew(false);
			}
			else {
				contentImage = (ContentImage)session.merge(contentImage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ContentImageModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { contentImageModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { contentImageModelImpl.getContentOriginId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTORIGINID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTORIGINID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((contentImageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentImageModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { contentImageModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((contentImageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTORIGINID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentImageModelImpl.getOriginalContentOriginId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTORIGINID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTORIGINID,
					args);

				args = new Object[] { contentImageModelImpl.getContentOriginId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTORIGINID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTORIGINID,
					args);
			}
		}

		entityCache.putResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
			ContentImageImpl.class, contentImage.getPrimaryKey(), contentImage,
			false);

		contentImage.resetOriginalValues();

		return contentImage;
	}

	/**
	 * Returns the content image with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the content image
	 * @return the content image
	 * @throws NoSuchContentImageException if a content image with the primary key could not be found
	 */
	@Override
	public ContentImage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContentImageException {
		ContentImage contentImage = fetchByPrimaryKey(primaryKey);

		if (contentImage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContentImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contentImage;
	}

	/**
	 * Returns the content image with the primary key or throws a {@link NoSuchContentImageException} if it could not be found.
	 *
	 * @param contentImageId the primary key of the content image
	 * @return the content image
	 * @throws NoSuchContentImageException if a content image with the primary key could not be found
	 */
	@Override
	public ContentImage findByPrimaryKey(long contentImageId)
		throws NoSuchContentImageException {
		return findByPrimaryKey((Serializable)contentImageId);
	}

	/**
	 * Returns the content image with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the content image
	 * @return the content image, or <code>null</code> if a content image with the primary key could not be found
	 */
	@Override
	public ContentImage fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
				ContentImageImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContentImage contentImage = (ContentImage)serializable;

		if (contentImage == null) {
			Session session = null;

			try {
				session = openSession();

				contentImage = (ContentImage)session.get(ContentImageImpl.class,
						primaryKey);

				if (contentImage != null) {
					cacheResult(contentImage);
				}
				else {
					entityCache.putResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
						ContentImageImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
					ContentImageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contentImage;
	}

	/**
	 * Returns the content image with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentImageId the primary key of the content image
	 * @return the content image, or <code>null</code> if a content image with the primary key could not be found
	 */
	@Override
	public ContentImage fetchByPrimaryKey(long contentImageId) {
		return fetchByPrimaryKey((Serializable)contentImageId);
	}

	@Override
	public Map<Serializable, ContentImage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContentImage> map = new HashMap<Serializable, ContentImage>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContentImage contentImage = fetchByPrimaryKey(primaryKey);

			if (contentImage != null) {
				map.put(primaryKey, contentImage);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
					ContentImageImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContentImage)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTENTIMAGE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ContentImage contentImage : (List<ContentImage>)q.list()) {
				map.put(contentImage.getPrimaryKeyObj(), contentImage);

				cacheResult(contentImage);

				uncachedPrimaryKeys.remove(contentImage.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContentImageModelImpl.ENTITY_CACHE_ENABLED,
					ContentImageImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the content images.
	 *
	 * @return the content images
	 */
	@Override
	public List<ContentImage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @return the range of content images
	 */
	@Override
	public List<ContentImage> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the content images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of content images
	 */
	@Override
	public List<ContentImage> findAll(int start, int end,
		OrderByComparator<ContentImage> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content images
	 * @param end the upper bound of the range of content images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of content images
	 */
	@Override
	public List<ContentImage> findAll(int start, int end,
		OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ContentImage> list = null;

		if (retrieveFromCache) {
			list = (List<ContentImage>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTENTIMAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTENTIMAGE;

				if (pagination) {
					sql = sql.concat(ContentImageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContentImage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentImage>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the content images from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContentImage contentImage : findAll()) {
			remove(contentImage);
		}
	}

	/**
	 * Returns the number of content images.
	 *
	 * @return the number of content images
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTENTIMAGE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ContentImageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the content image persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContentImageImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CONTENTIMAGE = "SELECT contentImage FROM ContentImage contentImage";
	private static final String _SQL_SELECT_CONTENTIMAGE_WHERE_PKS_IN = "SELECT contentImage FROM ContentImage contentImage WHERE contentImageId IN (";
	private static final String _SQL_SELECT_CONTENTIMAGE_WHERE = "SELECT contentImage FROM ContentImage contentImage WHERE ";
	private static final String _SQL_COUNT_CONTENTIMAGE = "SELECT COUNT(contentImage) FROM ContentImage contentImage";
	private static final String _SQL_COUNT_CONTENTIMAGE_WHERE = "SELECT COUNT(contentImage) FROM ContentImage contentImage WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contentImage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContentImage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContentImage exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContentImagePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}