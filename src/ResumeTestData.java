import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        Map<ContactType, String> contacts = new HashMap<>();

        contacts.put(ContactType.PHONE_NUMBER, "88005553535");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN_PROFILE, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB_PROFILE, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW_PROFILE, "https://stackoverflow.com/users/548473/grigory-kislin");
        contacts.put(ContactType.HOME_PAGE, "http://gkislin.ru/");

        resume.setContacts(contacts);

        Map<SectionType, Section> sections = new HashMap<>();
        TextSection personal = new TextSection();
        TextSection position = new TextSection();
        ListSection achievement = new ListSection();
        ListSection qualifications = new ListSection();
        CompanySection experience = new CompanySection();
        CompanySection education = new CompanySection();

        Period period = new Period();
        period.setDescription("Создание, организация и проведение Java онлайн проектов и стажировок.");

        personal.setText("Аналитический склад ума, сильная логика, + креативность, инициативность. Пурист кода и " +
                "архитектуры.");
        position.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        List<String> achievementList = new ArrayList<>();
        achievementList.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов " +
                "на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для " +
                "комплексных DIY смет");
        achievementList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        achievement.setList(achievementList);

        resume.setSections(sections);

        List<Company> companies = new ArrayList<>();
        List<Period> periods = new ArrayList<>();
        Company company1 = new Company();

        company1.setName("СПНИУИТМиТ");
        company1.setWebsite("www.spbu.ru");
        company1.setPeriods(periods);

        companies.add(company1);

        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.OBJECTIVE, position);
        sections.put(SectionType.ACHIEVEMENT, achievement);
        sections.put(SectionType.QUALIFICATIONS, qualifications);
        sections.put(SectionType.EXPERIENCE, experience);
        sections.put(SectionType.EDUCATION, education);

        resume.setSections(sections);

        return resume;
    }
}
