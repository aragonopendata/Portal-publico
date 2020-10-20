package es.aragon.barse.journalarticle.indexer.post.processor;

import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;

/**
 * @author migarcia
 */
@Component(
		immediate = true,
		property = {
			"indexer.class.name=com.liferay.journal.model.JournalArticle",
		},
		service = IndexerPostProcessor.class
	)
public class JournalarticleIndexerPostProcessor  implements IndexerPostProcessor {

	@Override
	public void postProcessDocument(Document document, Object obj) throws Exception {
		
		JournalArticle journalArticle = (JournalArticle)obj;
		
		// WBA-2367 - START - Expire content by Expiration Date isn't showed this in Asset Publisher
		// Added this because when in BaseIndexer:752 tries to set the expirationDate of the content,
		// the asset doesn't have the date saved and it gets indexed wrong (from the Control panel)
		
		if (journalArticle.getReviewDate() != null){
			document.addDate("reviewDate", journalArticle.getReviewDate());
		}
	}
	@Override
	public void postProcessContextQuery(BooleanQuery contextQuery, SearchContext searchContext) throws Exception {
		// It's not necessary to implement this method
	}


	@Override
	public void postProcessFullQuery(BooleanQuery fullQuery, SearchContext searchContext) throws Exception {
		// It's not necessary to implement this method
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext) throws Exception {
		// It's not necessary to implement this method
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, SearchContext searchContext) throws Exception {
		// It's not necessary to implement this method
	}

	@Override
	public void postProcessSummary(Summary summary, Document document, Locale locale, String snippet) {
		// It's not necessary to implement this method
	}
	@Override
	public void postProcessContextBooleanFilter(BooleanFilter booleanFilter, SearchContext searchContext)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}