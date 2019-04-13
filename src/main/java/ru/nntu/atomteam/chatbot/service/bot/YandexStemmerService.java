package ru.nntu.atomteam.chatbot.service.bot;

import org.springframework.stereotype.Service;
import ru.nntu.atomteam.chatbot.config.ConstantProvider;
import ru.nntu.atomteam.chatbot.util.AtomChatBotException;
import ru.stachek66.nlp.mystem.holding.Factory;
import ru.stachek66.nlp.mystem.holding.MyStem;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.holding.Request;
import ru.stachek66.nlp.mystem.model.Info;
import scala.Option;
import scala.collection.JavaConversions;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class YandexStemmerService {
	private final static MyStem STEMMER = new Factory(ConstantProvider.YANDEX_STEMMER_PARAMS)
			.newMyStem(ConstantProvider.YANDEX_STEMMER_VERSION, Option.empty()).get();

	public Set<String> normilize(String source) {
		final Iterable<Info> iterable;
		try {
			iterable = JavaConversions.asJavaIterable(
					STEMMER.analyze(Request.apply(source))
							.info()
							.toIterable());
		} catch (MyStemApplicationException e) {
			throw new AtomChatBotException("Yandex Stemmer error occured", e);
		}

		var result = new HashSet<String>();
		for (final Info info : iterable) {
			try {
				result.add(info.lex().get());
			}
			catch (NoSuchElementException e) {
				// just skip
			}
		}
		return result;
	}
}
