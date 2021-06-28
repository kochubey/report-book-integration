package logbook.dbl;

import logbook.dbl.exchange.Events;
import logbook.dbl.exchange.ExchangeObjects;
import logbook.dbl.exchange.Files;
import logbook.dbl.system.*;
import logbook.dbl.system.catalog.Attributes;
import logbook.dbl.system.schema.Schemas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"logbook.dbl"})
public class Main implements CommandLineRunner {

    @Autowired
    Dictionaries dicts;

    @Autowired
    Users users;

    @Autowired
    Abonents abonents;

    @Autowired
    Schemas schemas;

    @Autowired
    ExchangeObjects exchangeObjects;

    @Autowired
    Files files;

    @Autowired
    Events events;

    @Autowired
    Attributes attributes;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        initDb();

//        attributes.findAll().forEach(d -> System.out.println(d.getId() + " -> " + d.getName()));
//        dicts.findAll().forEach(d -> System.out.println(d.getCategory() + " -> " + d.getName()));
//        users.findAll().forEach(u -> System.out.println(u.getId() + " -> " + u.getLogin()));
//        abonents.findAll().forEach(a -> System.out.println(a.getId() + " -> " + a.getNamespace()));
//        schemas.findAll().forEach(s -> System.out.println(s.getId() + " -> " + s.getNamespace()));
//        exchanges.findAll().forEach(e -> System.out.println(e.getId() + " -> " + e.getUuid() + " : " + e.getEvents().size() + " | " + e.getFiles().size()));
    }

    private void initDb(){


//        Abonent fns = abonents.findByCode("fns").get();
//        Schema vo_svtorgsb = schemas.findByCode("vo_svtorgsb").get();

//        Schema vo_svtorgsb = schemas.findByCode("VO_SVTORGSB").get();
//        Schema vo_akttorgsb = schemas.findByCode("VO_AKTTORGSB").get();
//        Schema sved_ipp = schemas.findByCode("SVED_IPP").get();
//        Schema nio_bs = schemas.findByCode("nio1.10.2bs").get();
//        Schema nio_ks = schemas.findByCode("nio1.10.2ks").get();
//        Schema nifl = schemas.findByCode("nifl1.5.1.1").get();

/*

        //users.save(new User("111","1222","333","","123@com3.tech"));
        User u1 = users.findByLogin("111").get();
        //User u1 = users.findByLogin("amanchuk").get();
        exchangeObjects.save(new ExchangeObject(vo_svtorgsb,u1,UUID.randomUUID().toString(), UUID.randomUUID().toString(),"smx",null,null,null,null));
*/

        //attributes.deleteAll();

//        attributes.save(new Attribute("ИдОтпр",vo_akttorgsb));
//        attributes.save(new Attribute("ФИООтв",vo_akttorgsb));
//        attributes.save(new Attribute("Отчество",vo_akttorgsb));
//        attributes.save(new Attribute("Имя",vo_akttorgsb));
//        attributes.save(new Attribute("Фамилия",vo_akttorgsb));
//        attributes.save(new Attribute("ДолжОтв",vo_akttorgsb));
//        attributes.save(new Attribute("Документ",vo_akttorgsb));
//        attributes.save(new Attribute("ОписПерСвед",vo_akttorgsb));
//        attributes.save(new Attribute("ФИОПодп",vo_akttorgsb));
//        attributes.save(new Attribute("КодНО",vo_akttorgsb));
//        attributes.save(new Attribute("КПП",vo_akttorgsb));
//        attributes.save(new Attribute("ИННЮЛ",vo_akttorgsb));


//        attributes.save(new Attribute("НаимОрг",vo_akttorgsb));
//        attributes.save(new Attribute("СодПерСвед",vo_akttorgsb));
//        attributes.save(new Attribute("ИнфОбъект",vo_akttorgsb));
//        attributes.save(new Attribute("ФИОАкт",vo_akttorgsb));
//        attributes.save(new Attribute("СтавкаОбТорг",vo_akttorgsb));
//        attributes.save(new Attribute("СвПлат",vo_akttorgsb));
//        attributes.save(new Attribute("СвЮЛ",vo_akttorgsb));
//        attributes.save(new Attribute("ОГРН",vo_akttorgsb));
//        attributes.save(new Attribute("СвИП",vo_akttorgsb));
//        attributes.save(new Attribute("ФИОИП",vo_akttorgsb));
//        attributes.save(new Attribute("ОГРНИП",vo_akttorgsb));
//        attributes.save(new Attribute("АдрОбТорг",vo_akttorgsb));
//        attributes.save(new Attribute("Кварт",vo_akttorgsb));
//        attributes.save(new Attribute("Корпус",vo_akttorgsb));
//        attributes.save(new Attribute("Дом",vo_akttorgsb));
//        attributes.save(new Attribute("Улица",vo_akttorgsb));
//        attributes.save(new Attribute("КодРегион",vo_akttorgsb));
//        attributes.save(new Attribute("Индекс",vo_akttorgsb));
//        attributes.save(new Attribute("СумСборПер",vo_akttorgsb));
//        attributes.save(new Attribute("СумСборКв",vo_akttorgsb));
//        attributes.save(new Attribute("КодЛьгот",vo_akttorgsb));
//        attributes.save(new Attribute("СумЛьгот",vo_akttorgsb));
//        attributes.save(new Attribute("ИсчислКв",vo_akttorgsb));
//        attributes.save(new Attribute("ПлощОбТорг",vo_akttorgsb));
//        attributes.save(new Attribute("КадастНомЗУ",vo_akttorgsb));
//        attributes.save(new Attribute("КадастНомПом",vo_akttorgsb));
//        attributes.save(new Attribute("КадастрНомЗд",vo_akttorgsb));
//        attributes.save(new Attribute("НомерРазр",vo_akttorgsb));
//        attributes.save(new Attribute("РазмНТО",vo_akttorgsb));
//        attributes.save(new Attribute("НаимТоргОб",vo_akttorgsb));
//        attributes.save(new Attribute("ОКТМО",vo_akttorgsb));
//        attributes.save(new Attribute("ИдОб",vo_akttorgsb));
//        attributes.save(new Attribute("КодВидОб",vo_akttorgsb));
//        attributes.save(new Attribute("КодВидТорг",vo_akttorgsb));
//        attributes.save(new Attribute("ПрПлат",vo_akttorgsb));
//        attributes.save(new Attribute("КолКвартал",vo_akttorgsb));
//        attributes.save(new Attribute("ДатаВозникИсп",vo_akttorgsb));
//        attributes.save(new Attribute("НомАкт",vo_akttorgsb));
//        attributes.save(new Attribute("ДатаАкт",vo_akttorgsb));
//        attributes.save(new Attribute("ВидАкт",vo_akttorgsb));
//        attributes.save(new Attribute("ДатаДок",vo_akttorgsb));
//        attributes.save(new Attribute("ИдДок",vo_akttorgsb));
//        attributes.save(new Attribute("КолДок",vo_akttorgsb));
//        attributes.save(new Attribute("ВерсПрог",vo_akttorgsb));
//        attributes.save(new Attribute("ТипИнф",vo_akttorgsb));
//        attributes.save(new Attribute("ВерсФорм",vo_akttorgsb));
//        attributes.save(new Attribute("ИдФайл",vo_akttorgsb));
//        attributes.save(new Attribute("СведУвед",vo_svtorgsb));
//        attributes.save(new Attribute("СвНП",vo_svtorgsb));
//        attributes.save(new Attribute("НПФЛ",vo_svtorgsb));
//        attributes.save(new Attribute("ФИО",vo_svtorgsb));

//        attributes.save(new Attribute("Фамилия",vo_svtorgsb));
//        attributes.save(new Attribute("Имя",vo_svtorgsb));
//        attributes.save(new Attribute("Отчество",vo_svtorgsb));

//        attributes.save(new Attribute("ИННФЛ",vo_svtorgsb));
//        attributes.save(new Attribute("ОГРНИП",vo_svtorgsb));
//        attributes.save(new Attribute("ПрТоргСбор",vo_svtorgsb));
//        attributes.save(new Attribute("АннулирТоргСбор",vo_svtorgsb));
//        attributes.save(new Attribute("АннулирОбъекты",vo_svtorgsb));
//        attributes.save(new Attribute("ИдАннулирОб",vo_svtorgsb));
//        attributes.save(new Attribute("ИдАннулирДок",vo_svtorgsb));
//        attributes.save(new Attribute("ПричАннулир",vo_svtorgsb));
//        attributes.save(new Attribute("ДатаАннулир",vo_svtorgsb));
//        attributes.save(new Attribute("ИдДок",vo_svtorgsb));
//        attributes.save(new Attribute("ДатаДок",vo_svtorgsb));
//        attributes.save(new Attribute("КодНОИст",vo_svtorgsb));
//        attributes.save(new Attribute("ДатаРег",vo_svtorgsb));
//        attributes.save(new Attribute("ДатаВвод",vo_svtorgsb));
//        attributes.save(new Attribute("ДатаСинхр",vo_svtorgsb));
//        attributes.save(new Attribute("Файл",sved_ipp));
//        attributes.save(new Attribute("ИдОтпр",sved_ipp));
//        attributes.save(new Attribute("ФИООтв",sved_ipp));
//        attributes.save(new Attribute("Фамилия",sved_ipp));
//        attributes.save(new Attribute("Имя",sved_ipp));
//        attributes.save(new Attribute("Отчество",sved_ipp));
//        attributes.save(new Attribute("ДолжОтв",sved_ipp));
//        attributes.save(new Attribute("Тлф",sved_ipp));
//        attributes.save(new Attribute("Документ",sved_ipp));
//        attributes.save(new Attribute("ОтвЗап",sved_ipp));
//        attributes.save(new Attribute("КодСвед",sved_ipp));
//        attributes.save(new Attribute("СвНП",sved_ipp));
//        attributes.save(new Attribute("НПЮЛ",sved_ipp));
//        attributes.save(new Attribute("НаимОрг",sved_ipp));
//        attributes.save(new Attribute("ИННЮЛ",sved_ipp));
//        attributes.save(new Attribute("КПП",sved_ipp));
//        attributes.save(new Attribute("СвУчетНП",sved_ipp));
//        attributes.save(new Attribute("ПостНОМскв",sved_ipp));
//        attributes.save(new Attribute("ОКВЭД",sved_ipp));
//        attributes.save(new Attribute("ПризнУСН",sved_ipp));
//        attributes.save(new Attribute("Свед0200",sved_ipp));
//        attributes.save(new Attribute("АдрОП",sved_ipp));
//        attributes.save(new Attribute("АдрКЛАДР",sved_ipp));
//        attributes.save(new Attribute("Индекс",sved_ipp));
//        attributes.save(new Attribute("КодРегион",sved_ipp));
//        attributes.save(new Attribute("Город",sved_ipp));
//        attributes.save(new Attribute("Улица",sved_ipp));
//        attributes.save(new Attribute("Дом",sved_ipp));
//        attributes.save(new Attribute("Кварт",sved_ipp));
//        attributes.save(new Attribute("Корпус",sved_ipp));
//        attributes.save(new Attribute("НаселПункт",sved_ipp));
//        attributes.save(new Attribute("НаимОП",sved_ipp));
//        attributes.save(new Attribute("КПП_ОП",sved_ipp));
//        attributes.save(new Attribute("ДатаПостУч",sved_ipp));
//        attributes.save(new Attribute("ДатаСнятУч",sved_ipp));
//        attributes.save(new Attribute("Свед0300",sved_ipp));
//        attributes.save(new Attribute("ПриобрВнАктив",sved_ipp));
//        attributes.save(new Attribute("СумОтч",sved_ipp));
//        attributes.save(new Attribute("СумПред",sved_ipp));
//        attributes.save(new Attribute("ОКУД",sved_ipp));
//        attributes.save(new Attribute("Период",sved_ipp));
//        attributes.save(new Attribute("ОтчетГод",sved_ipp));
//        attributes.save(new Attribute("НомКорр",sved_ipp));
//        attributes.save(new Attribute("ОКЕИ",sved_ipp));
//        attributes.save(new Attribute("Свед1100",sved_ipp));
//        attributes.save(new Attribute("ДеклНДС",sved_ipp));
//        attributes.save(new Attribute("СумОпер7",sved_ipp));
//        attributes.save(new Attribute("КодОпер",sved_ipp));
//        attributes.save(new Attribute("СтРеалТов",sved_ipp));
//        attributes.save(new Attribute("ДеклЗем",sved_ipp));
//        attributes.save(new Attribute("СвДеклЗем",sved_ipp));
//        attributes.save(new Attribute("СвНалЛьготЗН",sved_ipp));
//        attributes.save(new Attribute("НомКадастрЗУ",sved_ipp));
//        attributes.save(new Attribute("КатегорЗем",sved_ipp));
//        attributes.save(new Attribute("СтКадастрЗУ",sved_ipp));
//        attributes.save(new Attribute("ДоляЗУ",sved_ipp));
//        attributes.save(new Attribute("НалСтав",sved_ipp));
//        attributes.save(new Attribute("КодЛьгот387_2УмСум",sved_ipp));
//        attributes.save(new Attribute("ОКТМО",sved_ipp));
//        attributes.save(new Attribute("КодНО",sved_ipp));
//        attributes.save(new Attribute("ПоМесту",sved_ipp));
//        attributes.save(new Attribute("ДеклПриб",sved_ipp));
//        attributes.save(new Attribute("НалБазаСтав",sved_ipp));
//        attributes.save(new Attribute("РаспрНалСубРФ",sved_ipp));
//        attributes.save(new Attribute("ТипНП",sved_ipp));
//        attributes.save(new Attribute("ОбРасч",sved_ipp));
//        attributes.save(new Attribute("ОбязУплНалОП",sved_ipp));
//        attributes.save(new Attribute("КППОП",sved_ipp));
//        attributes.save(new Attribute("НалБазаДоля",sved_ipp));
//        attributes.save(new Attribute("ДоляНалБаз",sved_ipp));
//        attributes.save(new Attribute("СтавНалСубРФ",sved_ipp));
//        attributes.save(new Attribute("НалБазаИсч",sved_ipp));
//        attributes.save(new Attribute("СтавНалСуб284",sved_ipp));
//        attributes.save(new Attribute("ДеклИмущ",sved_ipp));
//        attributes.save(new Attribute("СвНСиЛОбИм",sved_ipp));
//        attributes.save(new Attribute("ВидИмущ",sved_ipp));
//        attributes.save(new Attribute("НомКадЗдан",sved_ipp));
//        attributes.save(new Attribute("КодНалЛьг",sved_ipp));
//        attributes.save(new Attribute("КодЛгПНС",sved_ipp));
//        attributes.save(new Attribute("КодЛгУмен",sved_ipp));
//        attributes.save(new Attribute("Свед1200",sved_ipp));
//        attributes.save(new Attribute("Декл6НДФЛ",sved_ipp));
//        attributes.save(new Attribute("СумДох",sved_ipp));
//        attributes.save(new Attribute("Ставка",sved_ipp));
//        attributes.save(new Attribute("НачислДох",sved_ipp));
//        attributes.save(new Attribute("Свед1300",sved_ipp));
//        attributes.save(new Attribute("РасчСВ_ОПС",sved_ipp));
//        attributes.save(new Attribute("ТарифПлат",sved_ipp));
//        attributes.save(new Attribute("КолСтрахЛицВс",sved_ipp));
//        attributes.save(new Attribute("КНД",sved_ipp));
//        attributes.save(new Attribute("ДатаНачПер",sved_ipp));
//        attributes.save(new Attribute("ДатаКонПер",sved_ipp));
//        attributes.save(new Attribute("ИдДок",sved_ipp));
//        attributes.save(new Attribute("ДатаДок",sved_ipp));
//        attributes.save(new Attribute("ДатаЗапрос",sved_ipp));
//        attributes.save(new Attribute("ИдФайл",sved_ipp));
//        attributes.save(new Attribute("ИдФайлЗапрос",sved_ipp));
//        attributes.save(new Attribute("ВерсФорм",sved_ipp));
//        attributes.save(new Attribute("ТипИнф",sved_ipp));
//        attributes.save(new Attribute("КолДок",sved_ipp));
//        attributes.save(new Attribute("УслНомНП",nio_bs));
//        attributes.save(new Attribute("КодНО",nio_bs));
//        attributes.save(new Attribute("ПоМесту",nio_bs));
//        attributes.save(new Attribute("НалПериод",nio_bs));
//        attributes.save(new Attribute("ОтчГод",nio_bs));
//        attributes.save(new Attribute("НП_НО",nio_bs));
//        attributes.save(new Attribute("Усл_код",nio_bs));
//        attributes.save(new Attribute("Р2_ОКТМО",nio_bs));
//        attributes.save(new Attribute("Р2_КодЛьгот160",nio_bs));
//        attributes.save(new Attribute("Р2_КодЛьготыПС",nio_bs));
//        attributes.save(new Attribute("Р2_НалСтавка",nio_bs));
//        attributes.save(new Attribute("Р2_КодЛьготыУМС",nio_bs));
//        attributes.save(new Attribute("КадастрНомер",nio_bs));
//        attributes.save(new Attribute("УсловНомер",nio_bs));
//        attributes.save(new Attribute("ИнвентарНомер",nio_bs));
//        attributes.save(new Attribute("КодОКОФ",nio_bs));
//        attributes.save(new Attribute("НомКорр",nio_bs));
//        attributes.save(new Attribute("Р2_СтоимИмущ",nio_bs));
//        attributes.save(new Attribute("Р2_СтНеОблИмущ",nio_bs));
//        attributes.save(new Attribute("Р2_НалБаза",nio_bs));
//        attributes.save(new Attribute("Р2_СумНалога",nio_bs));
//        attributes.save(new Attribute("Р2_СумАвПлат",nio_bs));
//        attributes.save(new Attribute("Р2_СумЛьготы",nio_bs));
//        attributes.save(new Attribute("ОстСтОснСр31_12",nio_bs));
//        attributes.save(new Attribute("ОстатСтоим31_12",nio_bs));
//        attributes.save(new Attribute("УслНомНП",nio_ks));
//        attributes.save(new Attribute("КодНО",nio_ks));
//        attributes.save(new Attribute("ПоМесту",nio_ks));
//        attributes.save(new Attribute("НалПериод",nio_ks));
//        attributes.save(new Attribute("ОтчГод",nio_ks));
//        attributes.save(new Attribute("НП_НО",nio_ks));
//        attributes.save(new Attribute("Усл_код",nio_ks));
//        attributes.save(new Attribute("Р3_ОКТМО",nio_ks));
//        attributes.save(new Attribute("Р3_КодЛьготы040",nio_ks));
//        attributes.save(new Attribute("Р3_КодЛьготы070",nio_ks));
//        attributes.save(new Attribute("Р3_КадНомЗд",nio_ks));
//        attributes.save(new Attribute("Р3_КадНомПом",nio_ks));
//        attributes.save(new Attribute("Р3_НалСтавка",nio_ks));
//        attributes.save(new Attribute("Р3_Коэф",nio_ks));
//        attributes.save(new Attribute("Р3_КодЛьготы110",nio_ks));
//        attributes.save(new Attribute("НомКорр",nio_ks));
//        attributes.save(new Attribute("Р3_КадастрСтоим",nio_ks));
//        attributes.save(new Attribute("Р3_КадаСтНеОбл",nio_ks));
//        attributes.save(new Attribute("Р3_НалБаза",nio_ks));
//        attributes.save(new Attribute("Р3_СумНалога",nio_ks));
//        attributes.save(new Attribute("Р3_CумАвПлат",nio_ks));
//        attributes.save(new Attribute("Р3_СумЛьготы",nio_ks));
//        attributes.save(new Attribute("№п/п",nifl));
//        attributes.save(new Attribute("ВидОб",nifl));
//        attributes.save(new Attribute("КадстрНом",nifl));
//        attributes.save(new Attribute("УН",nifl));
//        attributes.save(new Attribute("ДоляСобств",nifl));
//        attributes.save(new Attribute("СменаДоли",nifl));
//        attributes.save(new Attribute("КолМесВлад",nifl));
//        attributes.save(new Attribute("КолМесЛьгот",nifl));
//        attributes.save(new Attribute("КодЛьготы",nifl));
//        attributes.save(new Attribute("ОбъектВПеречне",nifl));
//        attributes.save(new Attribute("ОбъектВРАп",nifl));
//        attributes.save(new Attribute("ИнфОСобств",nifl));
//        attributes.save(new Attribute("ИнвентСтоим",nifl));
//        attributes.save(new Attribute("СтавкаНалога",nifl));
//        attributes.save(new Attribute("ИсчислИнвент",nifl));
//        attributes.save(new Attribute("КУплатеИнвент",nifl));
//        attributes.save(new Attribute("ИсчислКадастр",nifl));
//        attributes.save(new Attribute("КУплатеКадастр",nifl));
//        attributes.save(new Attribute("ФИДОбСоб",nifl));
//        attributes.save(new Attribute("КадСтоим_ПОН КС",nifl));
//        attributes.save(new Attribute("ДатаРег",nifl));
//        attributes.save(new Attribute("ДатаПрекр",nifl));
//        attributes.save(new Attribute("ПлощОб_ПОНКС",nifl));
//        attributes.save(new Attribute("ПлощНалВыч_Общ",nifl));
//        attributes.save(new Attribute("УН_ОН",nifl));
//        attributes.save(new Attribute("СуммаЛьготы",nifl));
//        attributes.save(new Attribute("СуммЛьгот2014",nifl));
//        attributes.save(new Attribute("АдресОбНедвиж",nifl));
//        attributes.save(new Attribute("КодАдрКЛАДР",nifl));
//        attributes.save(new Attribute("YEAR_NP",nifl));





//        dicts.save(new Dictionary(null, "schemaTypes", "schemaTypes", "xml", "xml"));
//        dicts.save(new Dictionary(null, "schemaTypes", "schemaTypes", "json", "json"));
//        dicts.save(new Dictionary(null, "schemaTypes", "schemaTypes", "text", "text"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "daily", "Ежедневно"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "weekly", "Еженедельно"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "monthly", "Ежемесячно"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "quarterly", "Ежеквартально"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "yearly", "Ежегодно"));

//        users.save(new User("nkochubey","Николай","Кочубей","Александрович","nkochubey@inbox.ru"));
//        users.save(new User("avgizov","Алексей","Вагизов","Радиевич","avgizov@inbox.ru"));
//        users.save(new User("maovchinnikov","Михаил","Овчинников","Андреевич","maovchinnikov@inbox.ru"));
//        users.save(new User("amanchuk","Андрей","Манчук","","amanchuk@com3.tech"));
//
//        abonents.save(new Abonent("opn","АИС ОПН", "Автоматизированная информационная система координации работы органов исполнительной власти города Москвы по обеспечению поступления в бюджет города Москвы доходов от отдельных видов налогов и сборов"));
//        abonents.save(new Abonent("fns",	"АИС ФНС России", "Автоматизированная информационная система для автоматизации деятельности ФНС России"));
//        abonents.save(new Abonent("tensor", "Тензор", "ЛК Тензор", ""));

//        Abonent fns = abonents.findByCode("fns").get();
//        schemas.save(new Schema(
//                fns,
//                "vo_svtorgsb",
//                "1.0.0",
//                "Сведения о плательщиках и объектах обложения торговым сбором по уведомлениям о постановке на учет в качестве плательщика торгового сбора",
//                "Сведения о плательщиках и объектах обложения торговым сбором по уведомлениям о постановке на учет в качестве плательщика торгового сбора (внесении изменений показателей объекта осуществления торговли, прекращении объекта обложения сбором), по уведомлениям о снятии с учета в качестве плательщика торгового сбора, по актам о выявлении нового объекта обложения торговым сбором, актам о выявлении недостоверных сведений (включая сведения об отмене указанных актов)")
//        );
//        schemas.save(new Schema(
//                fns,
//                "vo_actsvd",
//                "1.0.0",
//                "Актуальные сведения по объектам, состоящим на учете в налоговом органе в рассматриваемом периоде, выгруженные по уведомлениям и актам",
//                "Актуальные сведения (показатели) по объектам, состоящим на учете в налоговом органе в рассматриваемом периоде, выгруженные по уведомлениям и актам")
//        );
//        schemas.save(new Schema(
//                fns,
//                "deprmpsn",
//                "1.0.0",
//                "Сведения об объектах индивидуальных предпринимателей, применяющих патентную систему налогообложения",
//                "Сведения об объектах индивидуальных предпринимателей, применяющих патентную систему налогообложения")
//        );
//
//        Abonent opn = abonents.findByCode("opn").get();
//        schemas.save(new Schema(
//                opn,
//                "vo_akttorgsb",
//                "1.0.0",
//                "Сведения об объектах обложения торговым сбором",
//                "Сведения об объектах обложения торговым сбором (подпункт 2 пункта 4.2.1.2 настоящего ТЗ)")
//        );
//        schemas.save(new Schema(
//                opn,
//                "vo_nedimkad",
//                "1.0.0",
//                "Сведения об объектах налогообложения, налоговая база по которым определяется как их кадастровая стоимость",
//                "Сведения об объектах налогообложения, налоговая база по которым определяется как их кадастровая стоимость")
//        );
//        schemas.save(new Schema(
//                opn,
//                "bigcomp_msk",
//                "1.0.0",
//                "Сведения о перечнях предприятий города Москвы, необходимые для формирования ряда агрегированных сведений",
//                "Сведения о перечнях предприятий города Москвы, необходимые для формирования ряда агрегированных сведений")
//        );
//
//        User u1 = users.findByLogin("nkochubey").get();
//        User u2 = users.findByLogin("amanchuk").get();
//
//        Schema vo_svtorgsb = schemas.findByCode("vo_svtorgsb").get();
//        Schema vo_actsvd = schemas.findByCode("vo_actsvd").get();
//        Schema deprmpsn = schemas.findByCode("deprmpsn").get();
//        Schema vo_akttorgsb = schemas.findByCode("vo_akttorgsb").get();
//        Schema vo_nedimkad = schemas.findByCode("vo_nedimkad").get();
//
//        exchanges.save(new Exchange(vo_svtorgsb, u1, new Date(), null));
//        exchanges.save(new Exchange(vo_svtorgsb, u1, new Date(), null));
//        exchanges.save(new Exchange(vo_svtorgsb, u2, new Date(), null));
//        exchanges.save(new Exchange(vo_svtorgsb, u2, new Date(), null));
//        exchanges.save(new Exchange(vo_svtorgsb, u1, new Date(), null));
//
//        exchanges.save(new Exchange(vo_actsvd, u1, new Date(), null));
//        exchanges.save(new Exchange(vo_actsvd, u2, new Date(), null));
//        exchanges.save(new Exchange(vo_actsvd, u2, new Date(), null));
//        exchanges.save(new Exchange(vo_actsvd, u1, new Date(), null));
//
//        exchanges.save(new Exchange(deprmpsn, u2, new Date(), null));
//        exchanges.save(new Exchange(deprmpsn, u2, new Date(), null));
//        exchanges.save(new Exchange(deprmpsn, u1, new Date(), null));
//
//        exchanges.save(new Exchange(vo_akttorgsb, u2, new Date(), null));
//        exchanges.save(new Exchange(vo_akttorgsb, u1, new Date(), null));
//
//        exchanges.save(new Exchange(vo_nedimkad, u1, new Date(), null));
    }
}
