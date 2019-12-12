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

import es.aragon.base.migration.exception.NoSuchContentMetadataException;
import es.aragon.base.migration.model.ContentMetadata;
import es.aragon.base.migration.model.impl.ContentMetadataImpl;
import es.aragon.base.migration.model.impl.ContentMetadataModelImpl;
import es.aragon.base.migration.service.persistence.ContentMetadataPersistence;

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
 * The persistence implementation for the content metadata service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentMetadataPersistence
 * @see es.aragon.base.migration.service.persistence.ContentMetadataUtil
 * @generated
 */
@ProviderType
public class ContentMetadataPersistenceImpl extends BasePersistenceImpl<ContentMetadata>
	implements ContentMetadataPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContentMetadataUtil} to access the content metadata persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContentMetadataImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataModelImpl.FINDER_CACHE_ENABLED,
			ContentMetadataImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataModelImpl.FINDER_CACHE_ENABLED,
			ContentMetadataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataModelImpl.FINDER_CACHE_ENABLED,
			ContentMetadataImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataModelImpl.FINDER_CACHE_ENABLED,
			ContentMetadataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ContentMetadataModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the content metadatas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching content metadatas
	 */
	@Override
	public List<ContentMetadata> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content metadatas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content metadatas
	 * @param end the upper bound of the range of content metadatas (not inclusive)
	 * @return the range of matching content metadatas
	 */
	@Override
	public List<ContentMetadata> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the content metadatas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content metadatas
	 * @param end the upper bound of the range of content metadatas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching content metadatas
	 */
	@Override
	public List<ContentMetadata> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentMetadata> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content metadatas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content metadatas
	 * @param end the upper bound of the range of content metadatas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching content metadatas
	 */
	@Override
	public List<ContentMetadata> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentMetadata> orderByComparator,
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

		List<ContentMetadata> list = null;

		if (retrieveFromCache) {
			list = (List<ContentMetadata>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContentMetadata contentMetadata : list) {
					if (!Objects.equals(uuid, contentMetadata.getUuid())) {
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

			query.append(_SQL_SELECT_CONTENTMETADATA_WHERE);

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
				query.append(ContentMetadataModelImpl.ORDER_BY_JPQL);
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
					list = (List<ContentMetadata>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentMetadata>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first content metadata in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content metadata
	 * @throws NoSuchContentMetadataException if a matching content metadata could not be found
	 */
	@Override
	public ContentMetadata findByUuid_First(String uuid,
		OrderByComparator<ContentMetadata> orderByComparator)
		throws NoSuchContentMetadataException {
		ContentMetadata contentMetadata = fetchByUuid_First(uuid,
				orderByComparator);

		if (contentMetadata != null) {
			return contentMetadata;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentMetadataException(msg.toString());
	}

	/**
	 * Returns the first content metadata in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content metadata, or <code>null</code> if a matching content metadata could not be found
	 */
	@Override
	public ContentMetadata fetchByUuid_First(String uuid,
		OrderByComparator<ContentMetadata> orderByComparator) {
		List<ContentMetadata> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content metadata in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content metadata
	 * @throws NoSuchContentMetadataException if a matching content metadata could not be found
	 */
	@Override
	public ContentMetadata findByUuid_Last(String uuid,
		OrderByComparator<ContentMetadata> orderByComparator)
		throws NoSuchContentMetadataException {
		ContentMetadata contentMetadata = fetchByUuid_Last(uuid,
				orderByComparator);

		if (contentMetadata != null) {
			return contentMetadata;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentMetadataException(msg.toString());
	}

	/**
	 * Returns the last content metadata in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content metadata, or <code>null</code> if a matching content metadata could not be found
	 */
	@Override
	public ContentMetadata fetchByUuid_Last(String uuid,
		OrderByComparator<ContentMetadata> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ContentMetadata> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the content metadatas before and after the current content metadata in the ordered set where uuid = &#63;.
	 *
	 * @param contentMetadataId the primary key of the current content metadata
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content metadata
	 * @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	 */
	@Override
	public ContentMetadata[] findByUuid_PrevAndNext(long contentMetadataId,
		String uuid, OrderByComparator<ContentMetadata> orderByComparator)
		throws NoSuchContentMetadataException {
		ContentMetadata contentMetadata = findByPrimaryKey(contentMetadataId);

		Session session = null;

		try {
			session = openSession();

			ContentMetadata[] array = new ContentMetadataImpl[3];

			array[0] = getByUuid_PrevAndNext(session, contentMetadata, uuid,
					orderByComparator, true);

			array[1] = contentMetadata;

			array[2] = getByUuid_PrevAndNext(session, contentMetadata, uuid,
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

	protected ContentMetadata getByUuid_PrevAndNext(Session session,
		ContentMetadata contentMetadata, String uuid,
		OrderByComparator<ContentMetadata> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENTMETADATA_WHERE);

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
			query.append(ContentMetadataModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(contentMetadata);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContentMetadata> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the content metadatas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ContentMetadata contentMetadata : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(contentMetadata);
		}
	}

	/**
	 * Returns the number of content metadatas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching content metadatas
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENTMETADATA_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "contentMetadata.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "contentMetadata.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(contentMetadata.uuid IS NULL OR contentMetadata.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CONTENTID = new FinderPath(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataModelImpl.FINDER_CACHE_ENABLED,
			ContentMetadataImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByContentId", new String[] { Long.class.getName() },
			ContentMetadataModelImpl.CONTENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTID = new FinderPath(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContentId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the content metadata where contentId = &#63; or throws a {@link NoSuchContentMetadataException} if it could not be found.
	 *
	 * @param contentId the content ID
	 * @return the matching content metadata
	 * @throws NoSuchContentMetadataException if a matching content metadata could not be found
	 */
	@Override
	public ContentMetadata findByContentId(long contentId)
		throws NoSuchContentMetadataException {
		ContentMetadata contentMetadata = fetchByContentId(contentId);

		if (contentMetadata == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contentId=");
			msg.append(contentId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchContentMetadataException(msg.toString());
		}

		return contentMetadata;
	}

	/**
	 * Returns the content metadata where contentId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param contentId the content ID
	 * @return the matching content metadata, or <code>null</code> if a matching content metadata could not be found
	 */
	@Override
	public ContentMetadata fetchByContentId(long contentId) {
		return fetchByContentId(contentId, true);
	}

	/**
	 * Returns the content metadata where contentId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param contentId the content ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching content metadata, or <code>null</code> if a matching content metadata could not be found
	 */
	@Override
	public ContentMetadata fetchByContentId(long contentId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { contentId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CONTENTID,
					finderArgs, this);
		}

		if (result instanceof ContentMetadata) {
			ContentMetadata contentMetadata = (ContentMetadata)result;

			if ((contentId != contentMetadata.getContentId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CONTENTMETADATA_WHERE);

			query.append(_FINDER_COLUMN_CONTENTID_CONTENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentId);

				List<ContentMetadata> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CONTENTID,
						finderArgs, list);
				}
				else {
					ContentMetadata contentMetadata = list.get(0);

					result = contentMetadata;

					cacheResult(contentMetadata);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CONTENTID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ContentMetadata)result;
		}
	}

	/**
	 * Removes the content metadata where contentId = &#63; from the database.
	 *
	 * @param contentId the content ID
	 * @return the content metadata that was removed
	 */
	@Override
	public ContentMetadata removeByContentId(long contentId)
		throws NoSuchContentMetadataException {
		ContentMetadata contentMetadata = findByContentId(contentId);

		return remove(contentMetadata);
	}

	/**
	 * Returns the number of content metadatas where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @return the number of matching content metadatas
	 */
	@Override
	public int countByContentId(long contentId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTENTID;

		Object[] finderArgs = new Object[] { contentId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENTMETADATA_WHERE);

			query.append(_FINDER_COLUMN_CONTENTID_CONTENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentId);

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

	private static final String _FINDER_COLUMN_CONTENTID_CONTENTID_2 = "contentMetadata.contentId = ?";

	public ContentMetadataPersistenceImpl() {
		setModelClass(ContentMetadata.class);

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
	 * Caches the content metadata in the entity cache if it is enabled.
	 *
	 * @param contentMetadata the content metadata
	 */
	@Override
	public void cacheResult(ContentMetadata contentMetadata) {
		entityCache.putResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataImpl.class, contentMetadata.getPrimaryKey(),
			contentMetadata);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CONTENTID,
			new Object[] { contentMetadata.getContentId() }, contentMetadata);

		contentMetadata.resetOriginalValues();
	}

	/**
	 * Caches the content metadatas in the entity cache if it is enabled.
	 *
	 * @param contentMetadatas the content metadatas
	 */
	@Override
	public void cacheResult(List<ContentMetadata> contentMetadatas) {
		for (ContentMetadata contentMetadata : contentMetadatas) {
			if (entityCache.getResult(
						ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
						ContentMetadataImpl.class,
						contentMetadata.getPrimaryKey()) == null) {
				cacheResult(contentMetadata);
			}
			else {
				contentMetadata.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all content metadatas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContentMetadataImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the content metadata.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContentMetadata contentMetadata) {
		entityCache.removeResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataImpl.class, contentMetadata.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ContentMetadataModelImpl)contentMetadata, true);
	}

	@Override
	public void clearCache(List<ContentMetadata> contentMetadatas) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContentMetadata contentMetadata : contentMetadatas) {
			entityCache.removeResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
				ContentMetadataImpl.class, contentMetadata.getPrimaryKey());

			clearUniqueFindersCache((ContentMetadataModelImpl)contentMetadata,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ContentMetadataModelImpl contentMetadataModelImpl) {
		Object[] args = new Object[] { contentMetadataModelImpl.getContentId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CONTENTID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CONTENTID, args,
			contentMetadataModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ContentMetadataModelImpl contentMetadataModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { contentMetadataModelImpl.getContentId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CONTENTID, args);
		}

		if ((contentMetadataModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CONTENTID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					contentMetadataModelImpl.getOriginalContentId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CONTENTID, args);
		}
	}

	/**
	 * Creates a new content metadata with the primary key. Does not add the content metadata to the database.
	 *
	 * @param contentMetadataId the primary key for the new content metadata
	 * @return the new content metadata
	 */
	@Override
	public ContentMetadata create(long contentMetadataId) {
		ContentMetadata contentMetadata = new ContentMetadataImpl();

		contentMetadata.setNew(true);
		contentMetadata.setPrimaryKey(contentMetadataId);

		String uuid = PortalUUIDUtil.generate();

		contentMetadata.setUuid(uuid);

		return contentMetadata;
	}

	/**
	 * Removes the content metadata with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentMetadataId the primary key of the content metadata
	 * @return the content metadata that was removed
	 * @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	 */
	@Override
	public ContentMetadata remove(long contentMetadataId)
		throws NoSuchContentMetadataException {
		return remove((Serializable)contentMetadataId);
	}

	/**
	 * Removes the content metadata with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the content metadata
	 * @return the content metadata that was removed
	 * @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	 */
	@Override
	public ContentMetadata remove(Serializable primaryKey)
		throws NoSuchContentMetadataException {
		Session session = null;

		try {
			session = openSession();

			ContentMetadata contentMetadata = (ContentMetadata)session.get(ContentMetadataImpl.class,
					primaryKey);

			if (contentMetadata == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContentMetadataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contentMetadata);
		}
		catch (NoSuchContentMetadataException nsee) {
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
	protected ContentMetadata removeImpl(ContentMetadata contentMetadata) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contentMetadata)) {
				contentMetadata = (ContentMetadata)session.get(ContentMetadataImpl.class,
						contentMetadata.getPrimaryKeyObj());
			}

			if (contentMetadata != null) {
				session.delete(contentMetadata);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contentMetadata != null) {
			clearCache(contentMetadata);
		}

		return contentMetadata;
	}

	@Override
	public ContentMetadata updateImpl(ContentMetadata contentMetadata) {
		boolean isNew = contentMetadata.isNew();

		if (!(contentMetadata instanceof ContentMetadataModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(contentMetadata.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(contentMetadata);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in contentMetadata proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ContentMetadata implementation " +
				contentMetadata.getClass());
		}

		ContentMetadataModelImpl contentMetadataModelImpl = (ContentMetadataModelImpl)contentMetadata;

		if (Validator.isNull(contentMetadata.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			contentMetadata.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (contentMetadata.isNew()) {
				session.save(contentMetadata);

				contentMetadata.setNew(false);
			}
			else {
				contentMetadata = (ContentMetadata)session.merge(contentMetadata);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ContentMetadataModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { contentMetadataModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((contentMetadataModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentMetadataModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { contentMetadataModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
			ContentMetadataImpl.class, contentMetadata.getPrimaryKey(),
			contentMetadata, false);

		clearUniqueFindersCache(contentMetadataModelImpl, false);
		cacheUniqueFindersCache(contentMetadataModelImpl);

		contentMetadata.resetOriginalValues();

		return contentMetadata;
	}

	/**
	 * Returns the content metadata with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the content metadata
	 * @return the content metadata
	 * @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	 */
	@Override
	public ContentMetadata findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContentMetadataException {
		ContentMetadata contentMetadata = fetchByPrimaryKey(primaryKey);

		if (contentMetadata == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContentMetadataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contentMetadata;
	}

	/**
	 * Returns the content metadata with the primary key or throws a {@link NoSuchContentMetadataException} if it could not be found.
	 *
	 * @param contentMetadataId the primary key of the content metadata
	 * @return the content metadata
	 * @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	 */
	@Override
	public ContentMetadata findByPrimaryKey(long contentMetadataId)
		throws NoSuchContentMetadataException {
		return findByPrimaryKey((Serializable)contentMetadataId);
	}

	/**
	 * Returns the content metadata with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the content metadata
	 * @return the content metadata, or <code>null</code> if a content metadata with the primary key could not be found
	 */
	@Override
	public ContentMetadata fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
				ContentMetadataImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContentMetadata contentMetadata = (ContentMetadata)serializable;

		if (contentMetadata == null) {
			Session session = null;

			try {
				session = openSession();

				contentMetadata = (ContentMetadata)session.get(ContentMetadataImpl.class,
						primaryKey);

				if (contentMetadata != null) {
					cacheResult(contentMetadata);
				}
				else {
					entityCache.putResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
						ContentMetadataImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
					ContentMetadataImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contentMetadata;
	}

	/**
	 * Returns the content metadata with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentMetadataId the primary key of the content metadata
	 * @return the content metadata, or <code>null</code> if a content metadata with the primary key could not be found
	 */
	@Override
	public ContentMetadata fetchByPrimaryKey(long contentMetadataId) {
		return fetchByPrimaryKey((Serializable)contentMetadataId);
	}

	@Override
	public Map<Serializable, ContentMetadata> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContentMetadata> map = new HashMap<Serializable, ContentMetadata>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContentMetadata contentMetadata = fetchByPrimaryKey(primaryKey);

			if (contentMetadata != null) {
				map.put(primaryKey, contentMetadata);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
					ContentMetadataImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContentMetadata)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTENTMETADATA_WHERE_PKS_IN);

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

			for (ContentMetadata contentMetadata : (List<ContentMetadata>)q.list()) {
				map.put(contentMetadata.getPrimaryKeyObj(), contentMetadata);

				cacheResult(contentMetadata);

				uncachedPrimaryKeys.remove(contentMetadata.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContentMetadataModelImpl.ENTITY_CACHE_ENABLED,
					ContentMetadataImpl.class, primaryKey, nullModel);
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
	 * Returns all the content metadatas.
	 *
	 * @return the content metadatas
	 */
	@Override
	public List<ContentMetadata> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content metadatas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content metadatas
	 * @param end the upper bound of the range of content metadatas (not inclusive)
	 * @return the range of content metadatas
	 */
	@Override
	public List<ContentMetadata> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the content metadatas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content metadatas
	 * @param end the upper bound of the range of content metadatas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of content metadatas
	 */
	@Override
	public List<ContentMetadata> findAll(int start, int end,
		OrderByComparator<ContentMetadata> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content metadatas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content metadatas
	 * @param end the upper bound of the range of content metadatas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of content metadatas
	 */
	@Override
	public List<ContentMetadata> findAll(int start, int end,
		OrderByComparator<ContentMetadata> orderByComparator,
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

		List<ContentMetadata> list = null;

		if (retrieveFromCache) {
			list = (List<ContentMetadata>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTENTMETADATA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTENTMETADATA;

				if (pagination) {
					sql = sql.concat(ContentMetadataModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContentMetadata>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentMetadata>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the content metadatas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContentMetadata contentMetadata : findAll()) {
			remove(contentMetadata);
		}
	}

	/**
	 * Returns the number of content metadatas.
	 *
	 * @return the number of content metadatas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTENTMETADATA);

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
		return ContentMetadataModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the content metadata persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContentMetadataImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CONTENTMETADATA = "SELECT contentMetadata FROM ContentMetadata contentMetadata";
	private static final String _SQL_SELECT_CONTENTMETADATA_WHERE_PKS_IN = "SELECT contentMetadata FROM ContentMetadata contentMetadata WHERE contentMetadataId IN (";
	private static final String _SQL_SELECT_CONTENTMETADATA_WHERE = "SELECT contentMetadata FROM ContentMetadata contentMetadata WHERE ";
	private static final String _SQL_COUNT_CONTENTMETADATA = "SELECT COUNT(contentMetadata) FROM ContentMetadata contentMetadata";
	private static final String _SQL_COUNT_CONTENTMETADATA_WHERE = "SELECT COUNT(contentMetadata) FROM ContentMetadata contentMetadata WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contentMetadata.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContentMetadata exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContentMetadata exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContentMetadataPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}