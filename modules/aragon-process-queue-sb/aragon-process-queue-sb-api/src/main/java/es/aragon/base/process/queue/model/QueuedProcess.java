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

package es.aragon.base.process.queue.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the QueuedProcess service. Represents a row in the &quot;EAB_PQ_QueuedProcess&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcessModel
 * @see es.aragon.base.process.queue.model.impl.QueuedProcessImpl
 * @see es.aragon.base.process.queue.model.impl.QueuedProcessModelImpl
 * @generated
 */
@ImplementationClassName("es.aragon.base.process.queue.model.impl.QueuedProcessImpl")
@ProviderType
public interface QueuedProcess extends QueuedProcessModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link es.aragon.base.process.queue.model.impl.QueuedProcessImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<QueuedProcess, Long> QUEUED_PROCESS_ID_ACCESSOR =
		new Accessor<QueuedProcess, Long>() {
			@Override
			public Long get(QueuedProcess queuedProcess) {
				return queuedProcess.getQueuedProcessId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<QueuedProcess> getTypeClass() {
				return QueuedProcess.class;
			}
		};
}