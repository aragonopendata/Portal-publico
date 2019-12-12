;(function() {
    var base = MODULE_PATH + '/js/';

    AUI().applyConfig(
        {
            groups: {
                mymodulesoverride: {
                    base: base,
                    combine: Liferay.AUI.getCombine(),
                    filter: Liferay.AUI.getFilterConfig(),
                    modules: {
                        'custom-liferay-ddm-form': {
                            path: 'liferay-ddm-form-override.js',
                            condition: {
                                name: 'custom-liferay-ddm-form',
                                trigger: 'liferay-ddm-form',
                                when: 'instead'
                            }
                        },
                        'custom-asset-categories-selector': {
                            path: 'asset-taglib-tags-selector-override.js',
                            condition: {
                                name: 'custom-asset-categories-selector',
                                trigger: 'liferay-asset-taglib-tags-selector',
                                when: 'instead'
                            }
                        }
                    },
                    root: base
                }
            }
        }
    );
})();