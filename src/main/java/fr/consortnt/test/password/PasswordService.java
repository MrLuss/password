package fr.consortnt.test.password;

import java.util.Arrays;
import java.util.List;

public interface PasswordService {

	public static final List<Character> SPECIAL_CHARACTERS = Arrays.asList('&', '!', '#', '@', '?', '_', '-');
	public String generate(int nbCharacters);
}
