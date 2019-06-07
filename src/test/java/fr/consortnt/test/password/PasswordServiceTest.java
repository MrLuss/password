package fr.consortnt.test.password;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.consortnt.test.password.impl.PasswordServiceImpl;

@RunWith(JUnit4.class)
public class PasswordServiceTest {

	private PasswordService passwordService;
	private static final int DEFAULT_NB_CHARS = 12;
	
	
	public PasswordServiceTest() {
		this.passwordService = new PasswordServiceImpl();
	}
	
	@Test
	public void passwordShouldContainsAtLeastOneCharInUpperCase() {
		for (int i = 0; i < 50; i++) {
			Assert.assertTrue(this.isOk(this.passwordService.generate(DEFAULT_NB_CHARS), 'A', 'Z'));
		}
	}
	
	@Test
	public void passwordShouldContainsAtLeastOneCharInLowerCase() {
		for (int i = 0; i < 50; i++) {
			Assert.assertTrue(this.isOk(this.passwordService.generate(DEFAULT_NB_CHARS), 'a', 'z'));
		}
	}
	
	@Test
	public void passwordShouldContainsAtLeastOneDigit() {
		for (int i = 0; i < 50; i++) {
			Assert.assertTrue(this.isOk(this.passwordService.generate(DEFAULT_NB_CHARS), '0', '9'));
		}
	}
	
	@Test
	public void passwordShouldContainsAtLeastOneSpecialCharacter() {
		for (int i = 0; i < 50; i++) {
			Assert.assertTrue(this.isOk(this.passwordService.generate(DEFAULT_NB_CHARS), PasswordService.SPECIAL_CHARACTERS));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nbCharacterShouldBeGreaterThan4() {
		this.passwordService.generate(4);
	}
	
	@Test
	public void passwordShouldBeUnique() {
		List<String> passwordGenerated = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			String password = this.passwordService.generate(DEFAULT_NB_CHARS);
			Assert.assertFalse(passwordGenerated.contains(password));
			passwordGenerated.add(password);
		}
	}
	
	private boolean isOk(String string, List<Character> characters) {
		boolean isOk = false;
		for(int j=0; j < string.length(); j++) {
			if (this.isOneOf(string.charAt(j), characters)) {
				isOk = true;
				break;
			}
		}
		return isOk;
	}
	
	private boolean isOk(String string, char lowerLimit, char upperLimit) {
		boolean isOk = false;
		for(int j=0; j < string.length(); j++) {
			if (this.isBetween(string.charAt(j), lowerLimit, upperLimit)) {
				isOk = true;
				break;
			}
		}
		return isOk;
	}

	private boolean isBetween(char character, char lowerLimit, char upperLimit) {
		return character >= lowerLimit && character <= upperLimit;
	}
	
	private boolean isOneOf(char character, List<Character> characters) {
		return characters.contains(Character.valueOf(character));
	}
}
