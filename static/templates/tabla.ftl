<#if fila.getSiblings()?has_content>
	<div class="container">
		<div class="tabla-coronavirus">
			<table aria-describedby="descTabla">
				<tbody>
					<#list fila.getSiblings() as cur_fila>
						<#if cur_fila?index == 0>
							<tr>
								<#if cur_fila.columna.getSiblings()?has_content>
									<#list cur_fila.columna.getSiblings() as cur_columna>
										<#if cur_columna.columnaTipo.getData() == "icono">
											<th aria-hidden="true">
												<#if cur_columna.documentoIcono.getData() != "">
													<span class="icon-theme icon-big" style="background-image: url(${cur_columna.documentoIcono.getData()});"></span>
												</#if>
											</th>
										<#else>
											<th>${cur_columna.columnaTexto.getData()}</th>
										</#if>
									</#list>
								</#if>
							</tr>
						<#else>
							<tr>
								<#if cur_fila.columna.getSiblings()?has_content>
									<#list cur_fila.columna.getSiblings() as cur_columna>
										<#if cur_columna.columnaTipo.getData() == "icono">
											<td aria-hidden="true">
												<#if cur_columna.documentoIcono.getData() != "">
													<span class="icon-theme icon-big" style="background-image: url(${cur_columna.documentoIcono.getData()});"></span>
												</#if>
											</td>
										<#else>
											<td>${cur_columna.columnaTexto.getData()}</td>
										</#if>
									</#list>
								</#if>
							</tr>
						</#if>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
</#if>