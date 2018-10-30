package lvlup;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApiTest {

    // GET /skills

        // returns a list of all skills (without battle list?)

    // GET /skills/{name}

        // returns individual skill information

    // POST /skills

        // creates and returns a new skill (info in request body)

    // PUT /skills/{name}

        // updates skill xp and returns updated skill (xp info in request body?)

    // DELETE /skills/{name}

        // deletes skill and all associated battles (skill id in request body?)

    // GET /skills/{name}/battles

        // lists all the associated battles

    // GET /skills/{name}/battles/{id}

        // returns individual battle information

    // POST /skills/{name}/battles

        // creates and returns a new battle and adds it to the skill (info in request body)

    // DELETE /skills/{name}/battles/{id}

        // deletes a battle (battle id in request body?)
}
