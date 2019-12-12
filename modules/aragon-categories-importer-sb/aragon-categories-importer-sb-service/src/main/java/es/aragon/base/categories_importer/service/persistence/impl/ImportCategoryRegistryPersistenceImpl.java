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

package es.aragon.base.categories_importer.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException;
import es.aragon.base.categories_importer.model.ImportCategoryRegistry;
import es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryImpl;
import es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryModelImpl;
import es.aragon.base.categories_importer.service.persistence.ImportCategoryRegistryPersistence;

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
 * The persistence implementation for the import category registry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistryPersistence
 * @see es.aragon.base.categories_importer.service.persistence.ImportCategoryRegistryUtil
 * @generated
 */
@ProviderType
public class ImportCategoryRegistryPersistenceImpl extends BasePersistenceImpl<ImportCategoryRegistry>
	implements ImportCategoryRegistryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImportCategoryRegistryUtil} to access the import category registry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImportCategoryRegistryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ImportCategoryRegistryModelImpl.UUID_COLUMN_BITMASK |
			ImportCategoryRegistryModelImpl.IMPORTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the import category registries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the import category registries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @return the range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the import category registries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByUuid(String uuid, int start,
		int end, OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the import category registries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByUuid(String uuid, int start,
		int end, OrderByComparator<ImportCategoryRegistry> orderByComparator,
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

		List<ImportCategoryRegistry> list = null;

		if (retrieveFromCache) {
			list = (List<ImportCategoryRegistry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ImportCategoryRegistry importCategoryRegistry : list) {
					if (!Objects.equals(uuid, importCategoryRegistry.getUuid())) {
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

			query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

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
				query.append(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
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
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
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
	 * Returns the first import category registry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByUuid_First(String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByUuid_First(uuid,
				orderByComparator);

		if (importCategoryRegistry != null) {
			return importCategoryRegistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchImportCategoryRegistryException(msg.toString());
	}

	/**
	 * Returns the first import category registry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByUuid_First(String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		List<ImportCategoryRegistry> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last import category registry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByUuid_Last(String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByUuid_Last(uuid,
				orderByComparator);

		if (importCategoryRegistry != null) {
			return importCategoryRegistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchImportCategoryRegistryException(msg.toString());
	}

	/**
	 * Returns the last import category registry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByUuid_Last(String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ImportCategoryRegistry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the import category registries before and after the current import category registry in the ordered set where uuid = &#63;.
	 *
	 * @param importCategoryRegistryId the primary key of the current import category registry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next import category registry
	 * @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry[] findByUuid_PrevAndNext(
		long importCategoryRegistryId, String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = findByPrimaryKey(importCategoryRegistryId);

		Session session = null;

		try {
			session = openSession();

			ImportCategoryRegistry[] array = new ImportCategoryRegistryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, importCategoryRegistry,
					uuid, orderByComparator, true);

			array[1] = importCategoryRegistry;

			array[2] = getByUuid_PrevAndNext(session, importCategoryRegistry,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ImportCategoryRegistry getByUuid_PrevAndNext(Session session,
		ImportCategoryRegistry importCategoryRegistry, String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

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
			query.append(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(importCategoryRegistry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ImportCategoryRegistry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the import category registries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ImportCategoryRegistry importCategoryRegistry : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(importCategoryRegistry);
		}
	}

	/**
	 * Returns the number of import category registries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching import category registries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IMPORTCATEGORYREGISTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "importCategoryRegistry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "importCategoryRegistry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(importCategoryRegistry.uuid IS NULL OR importCategoryRegistry.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_VOCABULARYID =
		new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByVocabularyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYID =
		new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByVocabularyId",
			new String[] { Long.class.getName() },
			ImportCategoryRegistryModelImpl.VOCABULARYID_COLUMN_BITMASK |
			ImportCategoryRegistryModelImpl.IMPORTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_VOCABULARYID = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByVocabularyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the import category registries where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @return the matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByVocabularyId(long vocabularyId) {
		return findByVocabularyId(vocabularyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the import category registries where vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @return the range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByVocabularyId(long vocabularyId,
		int start, int end) {
		return findByVocabularyId(vocabularyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the import category registries where vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByVocabularyId(long vocabularyId,
		int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return findByVocabularyId(vocabularyId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the import category registries where vocabularyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByVocabularyId(long vocabularyId,
		int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYID;
			finderArgs = new Object[] { vocabularyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_VOCABULARYID;
			finderArgs = new Object[] {
					vocabularyId,
					
					start, end, orderByComparator
				};
		}

		List<ImportCategoryRegistry> list = null;

		if (retrieveFromCache) {
			list = (List<ImportCategoryRegistry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ImportCategoryRegistry importCategoryRegistry : list) {
					if ((vocabularyId != importCategoryRegistry.getVocabularyId())) {
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

			query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				if (!pagination) {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
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
	 * Returns the first import category registry in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByVocabularyId_First(long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByVocabularyId_First(vocabularyId,
				orderByComparator);

		if (importCategoryRegistry != null) {
			return importCategoryRegistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchImportCategoryRegistryException(msg.toString());
	}

	/**
	 * Returns the first import category registry in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByVocabularyId_First(long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		List<ImportCategoryRegistry> list = findByVocabularyId(vocabularyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last import category registry in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByVocabularyId_Last(long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByVocabularyId_Last(vocabularyId,
				orderByComparator);

		if (importCategoryRegistry != null) {
			return importCategoryRegistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append("}");

		throw new NoSuchImportCategoryRegistryException(msg.toString());
	}

	/**
	 * Returns the last import category registry in the ordered set where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByVocabularyId_Last(long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		int count = countByVocabularyId(vocabularyId);

		if (count == 0) {
			return null;
		}

		List<ImportCategoryRegistry> list = findByVocabularyId(vocabularyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the import category registries before and after the current import category registry in the ordered set where vocabularyId = &#63;.
	 *
	 * @param importCategoryRegistryId the primary key of the current import category registry
	 * @param vocabularyId the vocabulary ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next import category registry
	 * @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry[] findByVocabularyId_PrevAndNext(
		long importCategoryRegistryId, long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = findByPrimaryKey(importCategoryRegistryId);

		Session session = null;

		try {
			session = openSession();

			ImportCategoryRegistry[] array = new ImportCategoryRegistryImpl[3];

			array[0] = getByVocabularyId_PrevAndNext(session,
					importCategoryRegistry, vocabularyId, orderByComparator,
					true);

			array[1] = importCategoryRegistry;

			array[2] = getByVocabularyId_PrevAndNext(session,
					importCategoryRegistry, vocabularyId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ImportCategoryRegistry getByVocabularyId_PrevAndNext(
		Session session, ImportCategoryRegistry importCategoryRegistry,
		long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

		query.append(_FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2);

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
			query.append(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(vocabularyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(importCategoryRegistry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ImportCategoryRegistry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the import category registries where vocabularyId = &#63; from the database.
	 *
	 * @param vocabularyId the vocabulary ID
	 */
	@Override
	public void removeByVocabularyId(long vocabularyId) {
		for (ImportCategoryRegistry importCategoryRegistry : findByVocabularyId(
				vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(importCategoryRegistry);
		}
	}

	/**
	 * Returns the number of import category registries where vocabularyId = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @return the number of matching import category registries
	 */
	@Override
	public int countByVocabularyId(long vocabularyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_VOCABULARYID;

		Object[] finderArgs = new Object[] { vocabularyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IMPORTCATEGORYREGISTRY_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

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

	private static final String _FINDER_COLUMN_VOCABULARYID_VOCABULARYID_2 = "importCategoryRegistry.vocabularyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_CATEGORYID = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCategoryId", new String[] { Long.class.getName() },
			ImportCategoryRegistryModelImpl.CATEGORYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYID = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCategoryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the import category registry where categoryId = &#63; or throws a {@link NoSuchImportCategoryRegistryException} if it could not be found.
	 *
	 * @param categoryId the category ID
	 * @return the matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByCategoryId(long categoryId)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByCategoryId(categoryId);

		if (importCategoryRegistry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("categoryId=");
			msg.append(categoryId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchImportCategoryRegistryException(msg.toString());
		}

		return importCategoryRegistry;
	}

	/**
	 * Returns the import category registry where categoryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param categoryId the category ID
	 * @return the matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByCategoryId(long categoryId) {
		return fetchByCategoryId(categoryId, true);
	}

	/**
	 * Returns the import category registry where categoryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param categoryId the category ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByCategoryId(long categoryId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { categoryId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CATEGORYID,
					finderArgs, this);
		}

		if (result instanceof ImportCategoryRegistry) {
			ImportCategoryRegistry importCategoryRegistry = (ImportCategoryRegistry)result;

			if ((categoryId != importCategoryRegistry.getCategoryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

				List<ImportCategoryRegistry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ImportCategoryRegistryPersistenceImpl.fetchByCategoryId(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ImportCategoryRegistry importCategoryRegistry = list.get(0);

					result = importCategoryRegistry;

					cacheResult(importCategoryRegistry);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CATEGORYID,
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
			return (ImportCategoryRegistry)result;
		}
	}

	/**
	 * Removes the import category registry where categoryId = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 * @return the import category registry that was removed
	 */
	@Override
	public ImportCategoryRegistry removeByCategoryId(long categoryId)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = findByCategoryId(categoryId);

		return remove(importCategoryRegistry);
	}

	/**
	 * Returns the number of import category registries where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the number of matching import category registries
	 */
	@Override
	public int countByCategoryId(long categoryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CATEGORYID;

		Object[] finderArgs = new Object[] { categoryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IMPORTCATEGORYREGISTRY_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

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

	private static final String _FINDER_COLUMN_CATEGORYID_CATEGORYID_2 = "importCategoryRegistry.categoryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { String.class.getName() },
			ImportCategoryRegistryModelImpl.TYPE_COLUMN_BITMASK |
			ImportCategoryRegistryModelImpl.IMPORTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the import category registries where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByType(String type) {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the import category registries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @return the range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByType(String type, int start,
		int end) {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the import category registries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByType(String type, int start,
		int end, OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return findByType(type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the import category registries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByType(String type, int start,
		int end, OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<ImportCategoryRegistry> list = null;

		if (retrieveFromCache) {
			list = (List<ImportCategoryRegistry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ImportCategoryRegistry importCategoryRegistry : list) {
					if (!Objects.equals(type, importCategoryRegistry.getType())) {
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

			query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else if (type.equals("")) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
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
	 * Returns the first import category registry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByType_First(String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByType_First(type,
				orderByComparator);

		if (importCategoryRegistry != null) {
			return importCategoryRegistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchImportCategoryRegistryException(msg.toString());
	}

	/**
	 * Returns the first import category registry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByType_First(String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		List<ImportCategoryRegistry> list = findByType(type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last import category registry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByType_Last(String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByType_Last(type,
				orderByComparator);

		if (importCategoryRegistry != null) {
			return importCategoryRegistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchImportCategoryRegistryException(msg.toString());
	}

	/**
	 * Returns the last import category registry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByType_Last(String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		int count = countByType(type);

		if (count == 0) {
			return null;
		}

		List<ImportCategoryRegistry> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the import category registries before and after the current import category registry in the ordered set where type = &#63;.
	 *
	 * @param importCategoryRegistryId the primary key of the current import category registry
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next import category registry
	 * @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry[] findByType_PrevAndNext(
		long importCategoryRegistryId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = findByPrimaryKey(importCategoryRegistryId);

		Session session = null;

		try {
			session = openSession();

			ImportCategoryRegistry[] array = new ImportCategoryRegistryImpl[3];

			array[0] = getByType_PrevAndNext(session, importCategoryRegistry,
					type, orderByComparator, true);

			array[1] = importCategoryRegistry;

			array[2] = getByType_PrevAndNext(session, importCategoryRegistry,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ImportCategoryRegistry getByType_PrevAndNext(Session session,
		ImportCategoryRegistry importCategoryRegistry, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_TYPE_TYPE_1);
		}
		else if (type.equals("")) {
			query.append(_FINDER_COLUMN_TYPE_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);
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
			query.append(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(importCategoryRegistry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ImportCategoryRegistry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the import category registries where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeByType(String type) {
		for (ImportCategoryRegistry importCategoryRegistry : findByType(type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(importCategoryRegistry);
		}
	}

	/**
	 * Returns the number of import category registries where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching import category registries
	 */
	@Override
	public int countByType(String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPE;

		Object[] finderArgs = new Object[] { type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IMPORTCATEGORYREGISTRY_WHERE);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else if (type.equals("")) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_TYPE_TYPE_1 = "importCategoryRegistry.type IS NULL";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "importCategoryRegistry.type = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_3 = "(importCategoryRegistry.type IS NULL OR importCategoryRegistry.type = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_VOCABULARYIDTYPE =
		new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByVocabularyIdType",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDTYPE =
		new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByVocabularyIdType",
			new String[] { Long.class.getName(), String.class.getName() },
			ImportCategoryRegistryModelImpl.VOCABULARYID_COLUMN_BITMASK |
			ImportCategoryRegistryModelImpl.TYPE_COLUMN_BITMASK |
			ImportCategoryRegistryModelImpl.IMPORTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_VOCABULARYIDTYPE = new FinderPath(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByVocabularyIdType",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the import category registries where vocabularyId = &#63; and type = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @return the matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type) {
		return findByVocabularyIdType(vocabularyId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the import category registries where vocabularyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @return the range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end) {
		return findByVocabularyIdType(vocabularyId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the import category registries where vocabularyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return findByVocabularyIdType(vocabularyId, type, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the import category registries where vocabularyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDTYPE;
			finderArgs = new Object[] { vocabularyId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_VOCABULARYIDTYPE;
			finderArgs = new Object[] {
					vocabularyId, type,
					
					start, end, orderByComparator
				};
		}

		List<ImportCategoryRegistry> list = null;

		if (retrieveFromCache) {
			list = (List<ImportCategoryRegistry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ImportCategoryRegistry importCategoryRegistry : list) {
					if ((vocabularyId != importCategoryRegistry.getVocabularyId()) ||
							!Objects.equals(type,
								importCategoryRegistry.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_VOCABULARYID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_1);
			}
			else if (type.equals("")) {
				query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
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
	 * Returns the first import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByVocabularyIdType_First(
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByVocabularyIdType_First(vocabularyId,
				type, orderByComparator);

		if (importCategoryRegistry != null) {
			return importCategoryRegistry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchImportCategoryRegistryException(msg.toString());
	}

	/**
	 * Returns the first import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByVocabularyIdType_First(
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		List<ImportCategoryRegistry> list = findByVocabularyIdType(vocabularyId,
				type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import category registry
	 * @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry findByVocabularyIdType_Last(
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByVocabularyIdType_Last(vocabularyId,
				type, orderByComparator);

		if (importCategoryRegistry != null) {
			return importCategoryRegistry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchImportCategoryRegistryException(msg.toString());
	}

	/**
	 * Returns the last import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByVocabularyIdType_Last(
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		int count = countByVocabularyIdType(vocabularyId, type);

		if (count == 0) {
			return null;
		}

		List<ImportCategoryRegistry> list = findByVocabularyIdType(vocabularyId,
				type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the import category registries before and after the current import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	 *
	 * @param importCategoryRegistryId the primary key of the current import category registry
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next import category registry
	 * @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry[] findByVocabularyIdType_PrevAndNext(
		long importCategoryRegistryId, long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = findByPrimaryKey(importCategoryRegistryId);

		Session session = null;

		try {
			session = openSession();

			ImportCategoryRegistry[] array = new ImportCategoryRegistryImpl[3];

			array[0] = getByVocabularyIdType_PrevAndNext(session,
					importCategoryRegistry, vocabularyId, type,
					orderByComparator, true);

			array[1] = importCategoryRegistry;

			array[2] = getByVocabularyIdType_PrevAndNext(session,
					importCategoryRegistry, vocabularyId, type,
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

	protected ImportCategoryRegistry getByVocabularyIdType_PrevAndNext(
		Session session, ImportCategoryRegistry importCategoryRegistry,
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE);

		query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_VOCABULARYID_2);

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_1);
		}
		else if (type.equals("")) {
			query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_2);
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
			query.append(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(vocabularyId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(importCategoryRegistry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ImportCategoryRegistry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the import category registries where vocabularyId = &#63; and type = &#63; from the database.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 */
	@Override
	public void removeByVocabularyIdType(long vocabularyId, String type) {
		for (ImportCategoryRegistry importCategoryRegistry : findByVocabularyIdType(
				vocabularyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(importCategoryRegistry);
		}
	}

	/**
	 * Returns the number of import category registries where vocabularyId = &#63; and type = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param type the type
	 * @return the number of matching import category registries
	 */
	@Override
	public int countByVocabularyIdType(long vocabularyId, String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_VOCABULARYIDTYPE;

		Object[] finderArgs = new Object[] { vocabularyId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_IMPORTCATEGORYREGISTRY_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_VOCABULARYID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_1);
			}
			else if (type.equals("")) {
				query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_VOCABULARYIDTYPE_VOCABULARYID_2 = "importCategoryRegistry.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_1 = "importCategoryRegistry.type IS NULL";
	private static final String _FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_2 = "importCategoryRegistry.type = ?";
	private static final String _FINDER_COLUMN_VOCABULARYIDTYPE_TYPE_3 = "(importCategoryRegistry.type IS NULL OR importCategoryRegistry.type = '')";

	public ImportCategoryRegistryPersistenceImpl() {
		setModelClass(ImportCategoryRegistry.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("type", "type_");
			dbColumnNames.put("comment", "comment_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the import category registry in the entity cache if it is enabled.
	 *
	 * @param importCategoryRegistry the import category registry
	 */
	@Override
	public void cacheResult(ImportCategoryRegistry importCategoryRegistry) {
		entityCache.putResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			importCategoryRegistry.getPrimaryKey(), importCategoryRegistry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
			new Object[] { importCategoryRegistry.getCategoryId() },
			importCategoryRegistry);

		importCategoryRegistry.resetOriginalValues();
	}

	/**
	 * Caches the import category registries in the entity cache if it is enabled.
	 *
	 * @param importCategoryRegistries the import category registries
	 */
	@Override
	public void cacheResult(
		List<ImportCategoryRegistry> importCategoryRegistries) {
		for (ImportCategoryRegistry importCategoryRegistry : importCategoryRegistries) {
			if (entityCache.getResult(
						ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
						ImportCategoryRegistryImpl.class,
						importCategoryRegistry.getPrimaryKey()) == null) {
				cacheResult(importCategoryRegistry);
			}
			else {
				importCategoryRegistry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all import category registries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImportCategoryRegistryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the import category registry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImportCategoryRegistry importCategoryRegistry) {
		entityCache.removeResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			importCategoryRegistry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ImportCategoryRegistryModelImpl)importCategoryRegistry,
			true);
	}

	@Override
	public void clearCache(
		List<ImportCategoryRegistry> importCategoryRegistries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImportCategoryRegistry importCategoryRegistry : importCategoryRegistries) {
			entityCache.removeResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
				ImportCategoryRegistryImpl.class,
				importCategoryRegistry.getPrimaryKey());

			clearUniqueFindersCache((ImportCategoryRegistryModelImpl)importCategoryRegistry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ImportCategoryRegistryModelImpl importCategoryRegistryModelImpl) {
		Object[] args = new Object[] {
				importCategoryRegistryModelImpl.getCategoryId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CATEGORYID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CATEGORYID, args,
			importCategoryRegistryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ImportCategoryRegistryModelImpl importCategoryRegistryModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					importCategoryRegistryModelImpl.getCategoryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CATEGORYID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CATEGORYID, args);
		}

		if ((importCategoryRegistryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CATEGORYID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					importCategoryRegistryModelImpl.getOriginalCategoryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CATEGORYID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CATEGORYID, args);
		}
	}

	/**
	 * Creates a new import category registry with the primary key. Does not add the import category registry to the database.
	 *
	 * @param importCategoryRegistryId the primary key for the new import category registry
	 * @return the new import category registry
	 */
	@Override
	public ImportCategoryRegistry create(long importCategoryRegistryId) {
		ImportCategoryRegistry importCategoryRegistry = new ImportCategoryRegistryImpl();

		importCategoryRegistry.setNew(true);
		importCategoryRegistry.setPrimaryKey(importCategoryRegistryId);

		String uuid = PortalUUIDUtil.generate();

		importCategoryRegistry.setUuid(uuid);

		return importCategoryRegistry;
	}

	/**
	 * Removes the import category registry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param importCategoryRegistryId the primary key of the import category registry
	 * @return the import category registry that was removed
	 * @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry remove(long importCategoryRegistryId)
		throws NoSuchImportCategoryRegistryException {
		return remove((Serializable)importCategoryRegistryId);
	}

	/**
	 * Removes the import category registry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the import category registry
	 * @return the import category registry that was removed
	 * @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry remove(Serializable primaryKey)
		throws NoSuchImportCategoryRegistryException {
		Session session = null;

		try {
			session = openSession();

			ImportCategoryRegistry importCategoryRegistry = (ImportCategoryRegistry)session.get(ImportCategoryRegistryImpl.class,
					primaryKey);

			if (importCategoryRegistry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImportCategoryRegistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(importCategoryRegistry);
		}
		catch (NoSuchImportCategoryRegistryException nsee) {
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
	protected ImportCategoryRegistry removeImpl(
		ImportCategoryRegistry importCategoryRegistry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(importCategoryRegistry)) {
				importCategoryRegistry = (ImportCategoryRegistry)session.get(ImportCategoryRegistryImpl.class,
						importCategoryRegistry.getPrimaryKeyObj());
			}

			if (importCategoryRegistry != null) {
				session.delete(importCategoryRegistry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (importCategoryRegistry != null) {
			clearCache(importCategoryRegistry);
		}

		return importCategoryRegistry;
	}

	@Override
	public ImportCategoryRegistry updateImpl(
		ImportCategoryRegistry importCategoryRegistry) {
		boolean isNew = importCategoryRegistry.isNew();

		if (!(importCategoryRegistry instanceof ImportCategoryRegistryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(importCategoryRegistry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(importCategoryRegistry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in importCategoryRegistry proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ImportCategoryRegistry implementation " +
				importCategoryRegistry.getClass());
		}

		ImportCategoryRegistryModelImpl importCategoryRegistryModelImpl = (ImportCategoryRegistryModelImpl)importCategoryRegistry;

		if (Validator.isNull(importCategoryRegistry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			importCategoryRegistry.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (importCategoryRegistry.isNew()) {
				session.save(importCategoryRegistry);

				importCategoryRegistry.setNew(false);
			}
			else {
				importCategoryRegistry = (ImportCategoryRegistry)session.merge(importCategoryRegistry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ImportCategoryRegistryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					importCategoryRegistryModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					importCategoryRegistryModelImpl.getVocabularyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYID,
				args);

			args = new Object[] { importCategoryRegistryModelImpl.getType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
				args);

			args = new Object[] {
					importCategoryRegistryModelImpl.getVocabularyId(),
					importCategoryRegistryModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYIDTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDTYPE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((importCategoryRegistryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						importCategoryRegistryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { importCategoryRegistryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((importCategoryRegistryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						importCategoryRegistryModelImpl.getOriginalVocabularyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYID,
					args);

				args = new Object[] {
						importCategoryRegistryModelImpl.getVocabularyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYID,
					args);
			}

			if ((importCategoryRegistryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						importCategoryRegistryModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] { importCategoryRegistryModelImpl.getType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}

			if ((importCategoryRegistryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						importCategoryRegistryModelImpl.getOriginalVocabularyId(),
						importCategoryRegistryModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYIDTYPE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDTYPE,
					args);

				args = new Object[] {
						importCategoryRegistryModelImpl.getVocabularyId(),
						importCategoryRegistryModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYIDTYPE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDTYPE,
					args);
			}
		}

		entityCache.putResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
			ImportCategoryRegistryImpl.class,
			importCategoryRegistry.getPrimaryKey(), importCategoryRegistry,
			false);

		clearUniqueFindersCache(importCategoryRegistryModelImpl, false);
		cacheUniqueFindersCache(importCategoryRegistryModelImpl);

		importCategoryRegistry.resetOriginalValues();

		return importCategoryRegistry;
	}

	/**
	 * Returns the import category registry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the import category registry
	 * @return the import category registry
	 * @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImportCategoryRegistryException {
		ImportCategoryRegistry importCategoryRegistry = fetchByPrimaryKey(primaryKey);

		if (importCategoryRegistry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImportCategoryRegistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return importCategoryRegistry;
	}

	/**
	 * Returns the import category registry with the primary key or throws a {@link NoSuchImportCategoryRegistryException} if it could not be found.
	 *
	 * @param importCategoryRegistryId the primary key of the import category registry
	 * @return the import category registry
	 * @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry findByPrimaryKey(
		long importCategoryRegistryId)
		throws NoSuchImportCategoryRegistryException {
		return findByPrimaryKey((Serializable)importCategoryRegistryId);
	}

	/**
	 * Returns the import category registry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the import category registry
	 * @return the import category registry, or <code>null</code> if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
				ImportCategoryRegistryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImportCategoryRegistry importCategoryRegistry = (ImportCategoryRegistry)serializable;

		if (importCategoryRegistry == null) {
			Session session = null;

			try {
				session = openSession();

				importCategoryRegistry = (ImportCategoryRegistry)session.get(ImportCategoryRegistryImpl.class,
						primaryKey);

				if (importCategoryRegistry != null) {
					cacheResult(importCategoryRegistry);
				}
				else {
					entityCache.putResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
						ImportCategoryRegistryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
					ImportCategoryRegistryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return importCategoryRegistry;
	}

	/**
	 * Returns the import category registry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param importCategoryRegistryId the primary key of the import category registry
	 * @return the import category registry, or <code>null</code> if a import category registry with the primary key could not be found
	 */
	@Override
	public ImportCategoryRegistry fetchByPrimaryKey(
		long importCategoryRegistryId) {
		return fetchByPrimaryKey((Serializable)importCategoryRegistryId);
	}

	@Override
	public Map<Serializable, ImportCategoryRegistry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImportCategoryRegistry> map = new HashMap<Serializable, ImportCategoryRegistry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImportCategoryRegistry importCategoryRegistry = fetchByPrimaryKey(primaryKey);

			if (importCategoryRegistry != null) {
				map.put(primaryKey, importCategoryRegistry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
					ImportCategoryRegistryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImportCategoryRegistry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE_PKS_IN);

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

			for (ImportCategoryRegistry importCategoryRegistry : (List<ImportCategoryRegistry>)q.list()) {
				map.put(importCategoryRegistry.getPrimaryKeyObj(),
					importCategoryRegistry);

				cacheResult(importCategoryRegistry);

				uncachedPrimaryKeys.remove(importCategoryRegistry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImportCategoryRegistryModelImpl.ENTITY_CACHE_ENABLED,
					ImportCategoryRegistryImpl.class, primaryKey, nullModel);
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
	 * Returns all the import category registries.
	 *
	 * @return the import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the import category registries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @return the range of import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the import category registries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findAll(int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the import category registries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of import category registries
	 * @param end the upper bound of the range of import category registries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of import category registries
	 */
	@Override
	public List<ImportCategoryRegistry> findAll(int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
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

		List<ImportCategoryRegistry> list = null;

		if (retrieveFromCache) {
			list = (List<ImportCategoryRegistry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMPORTCATEGORYREGISTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMPORTCATEGORYREGISTRY;

				if (pagination) {
					sql = sql.concat(ImportCategoryRegistryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImportCategoryRegistry>)QueryUtil.list(q,
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
	 * Removes all the import category registries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImportCategoryRegistry importCategoryRegistry : findAll()) {
			remove(importCategoryRegistry);
		}
	}

	/**
	 * Returns the number of import category registries.
	 *
	 * @return the number of import category registries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMPORTCATEGORYREGISTRY);

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
		return ImportCategoryRegistryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the import category registry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImportCategoryRegistryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMPORTCATEGORYREGISTRY = "SELECT importCategoryRegistry FROM ImportCategoryRegistry importCategoryRegistry";
	private static final String _SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE_PKS_IN = "SELECT importCategoryRegistry FROM ImportCategoryRegistry importCategoryRegistry WHERE importCategoryRegistryId IN (";
	private static final String _SQL_SELECT_IMPORTCATEGORYREGISTRY_WHERE = "SELECT importCategoryRegistry FROM ImportCategoryRegistry importCategoryRegistry WHERE ";
	private static final String _SQL_COUNT_IMPORTCATEGORYREGISTRY = "SELECT COUNT(importCategoryRegistry) FROM ImportCategoryRegistry importCategoryRegistry";
	private static final String _SQL_COUNT_IMPORTCATEGORYREGISTRY_WHERE = "SELECT COUNT(importCategoryRegistry) FROM ImportCategoryRegistry importCategoryRegistry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "importCategoryRegistry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImportCategoryRegistry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImportCategoryRegistry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ImportCategoryRegistryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "type", "comment"
			});
}