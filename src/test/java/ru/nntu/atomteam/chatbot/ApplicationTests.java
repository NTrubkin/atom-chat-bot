package ru.nntu.atomteam.chatbot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Russian;
import org.languagetool.rules.RuleMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nntu.atomteam.chatbot.model.entity.Command;
import ru.nntu.atomteam.chatbot.model.entity.Tag;
import ru.nntu.atomteam.chatbot.repository.CommandRepository;
import ru.nntu.atomteam.chatbot.service.RosatomResourceClient;
import ru.nntu.atomteam.chatbot.service.bot.YandexStemmerService;
import ru.stachek66.nlp.mystem.holding.Factory;
import ru.stachek66.nlp.mystem.holding.MyStem;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.holding.Request;
import ru.stachek66.nlp.mystem.model.Info;
import scala.Option;
import scala.collection.JavaConversions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private CommandRepository commandRepository;
	private final static MyStem mystemAnalyzer =
			new Factory("-igd --eng-gr --format json --weight")
					.newMyStem("3.0", Option.<File>empty()).get();

	@Autowired
	private RosatomResourceClient client;

	@Autowired
	private YandexStemmerService yandexStemmerService;


	@Test
	public void testCommandRepositoryCrud() {
		var command = new Command("reply", "code", "desc", true);
		commandRepository.save(command);
		Assert.assertEquals(command, commandRepository.findById(command.getId()).orElseThrow());
		commandRepository.delete(command);
	}

	@Test
	public void testCommandTags() {
		var command = new Command("reply", "code", "desc", true);
		commandRepository.save(command);
		command.getTags().addAll(Set.of(new Tag("tag1", 1), new Tag("tag2", 2)));
		commandRepository.save(command);
		commandRepository.delete(command);

	}

	@Test
	public void yandexStemmerTester() throws MyStemApplicationException {
		final Iterable<Info> result =
				JavaConversions.asJavaIterable(
						mystemAnalyzer
								.analyze(Request.apply("инженеру-разработчику инженеру"))
								.info()
								.toIterable());

		for (final Info info : result) {
			System.out.println(info.initial() + " -> " + info.lex() + " | " + info.rawResponse());
		}

	}

	@Test
	public void testSpellCorrection() throws IOException {

		JLanguageTool langTool = new JLanguageTool(new Russian());
// comment in to use statistical ngram data:
//langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
		List<RuleMatch> matches = langTool.check("привет меня звут Никита");
		for (RuleMatch match : matches) {
			System.out.println("Potential error at characters " +
					match.getFromPos() + "-" + match.getToPos() + ": " +
					match.getMessage());
			System.out.println("Suggested correction(s): " +
					match.getSuggestedReplacements());
		}
	}

	@Test
	public void playground() {
		client.getHireDate("nt");
	}
}
