package ru.nntu.atomteam.chatbot.service.mapper;

/**
 *
 * @param <S> source
 * @param <T> target
 */
public interface Mapper<S, T> {
	T map(S source);
}
