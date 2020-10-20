<style>
    .covid-destacados-azules {
        margin: 5rem 0;
    }
    .covid-destacados-azules .introduccion {
        color: #333;
        font-family: Signika;
        font-size: 3.4rem;
        line-height: 4rem;
        font-weight: bold;
        letter-spacing: 0;
        margin-bottom: 4.5rem;
    }
    .covid-destacados-azules .contenedor-entrada {
    	margin-bottom: 1.5rem;
    }  
    .covid-destacados-azules .contenedor-entrada .entrada {
        box-shadow: 0 0 21px 0 rgba(0,0,0,0.2);
        border-bottom: solid 3px #146176;
        padding: 2rem 3rem;
        position: relative;
    }   
    .covid-destacados-azules .contenedor-entrada .entrada .enlace {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
        width: 85%;
        color: #000;
        word-break: break-word;
        padding-left: 9rem;
        font-size: 1.6rem;
        line-height: 2.4rem;
        text-transform: uppercase;
        font-weight: bold;
    } 
    @media (max-width: 992px) {
        .covid-destacados-azules .contenedor-entrada {
            margin-bottom: 2rem;
        } 
        .covid-destacados-azules .contenedor-entrada:last-child {
            margin-bottom: 0;
        }        
    }
</style>
<#if tema.getSiblings()?has_content>
    <section class="covid-destacados-azules" role="region">
        <div class="container">
            <h2 class="introduccion">${.vars['reserved-article-description'].data}</h2>
            <div class="row">
                <#list tema.getSiblings() as cur_link>
                    <div class="col-lg-4 contenedor-entrada">
                        <div class="entrada">
                            <#assign linkTarget = "">
                            <#if getterUtil.getBoolean(cur_link.blank.getData())>
                                <#assign linkTarget = "target='_blank'">
                            </#if>
                            <i class="icon-theme icon-big" style="background-image: url('${cur_link.icono.getData()}');"></i>
                            <a class="enlace" href="${cur_link.enlace.getData()}" ${linkTarget}>
                                <span>${cur_link.getData()}</span>
                            </a>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </section>
</#if>