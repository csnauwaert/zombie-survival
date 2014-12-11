package org.kerwyn.game;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GreetingRepositoryTest {

	@Mock
	private GreetingRepository greetingRepository;
	@Mock
	private Greeting greeting;

	@Test
	public void testMock() {

		when(greetingRepository.count()).thenReturn(1L);
		when(greetingRepository.findOne(1L)).thenReturn(greeting);
		doReturn(1L).when(greeting).getId();

		assertEquals(1L, greetingRepository.count());
		assertEquals(greeting, greetingRepository.findOne(1L));
		assertEquals(1L, greeting.getId());

	}

}
