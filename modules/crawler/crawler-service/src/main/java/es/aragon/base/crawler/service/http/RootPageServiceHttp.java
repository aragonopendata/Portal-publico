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

package es.aragon.base.crawler.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import es.aragon.base.crawler.service.RootPageServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link RootPageServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RootPageServiceSoap
 * @see HttpPrincipal
 * @see RootPageServiceUtil
 * @generated
 */
@ProviderType
public class RootPageServiceHttp {
	public static String getAlias(HttpPrincipal httpPrincipal, long pageId) {
		try {
			MethodKey methodKey = new MethodKey(RootPageServiceUtil.class,
					"getAlias", _getAliasParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, pageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.Date getCrawledDate(HttpPrincipal httpPrincipal,
		long pageId) {
		try {
			MethodKey methodKey = new MethodKey(RootPageServiceUtil.class,
					"getCrawledDate", _getCrawledDateParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, pageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.Date)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String getInclusionRules(HttpPrincipal httpPrincipal,
		long pageId) {
		try {
			MethodKey methodKey = new MethodKey(RootPageServiceUtil.class,
					"getInclusionRules", _getInclusionRulesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, pageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long getRootPageId(HttpPrincipal httpPrincipal, long pageId) {
		try {
			MethodKey methodKey = new MethodKey(RootPageServiceUtil.class,
					"getRootPageId", _getRootPageIdParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, pageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RootPageServiceHttp.class);
	private static final Class<?>[] _getAliasParameterTypes0 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCrawledDateParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getInclusionRulesParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getRootPageIdParameterTypes3 = new Class[] {
			long.class
		};
}