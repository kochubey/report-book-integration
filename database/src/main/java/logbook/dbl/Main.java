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

//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????",vo_akttorgsb));


//        attributes.save(new Attribute("??????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????????",vo_akttorgsb));
//        attributes.save(new Attribute("??????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????",vo_akttorgsb));
//        attributes.save(new Attribute("????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????",vo_svtorgsb));

//        attributes.save(new Attribute("??????????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????",vo_svtorgsb));
//        attributes.save(new Attribute("????????????????",vo_svtorgsb));

//        attributes.save(new Attribute("??????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????????????????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????????????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("??????????????????",vo_svtorgsb));
//        attributes.save(new Attribute("????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("??????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("??????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("????????",sved_ipp));
//        attributes.save(new Attribute("????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("??????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("????????0200",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("??????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("??????_????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????0300",sved_ipp));
//        attributes.save(new Attribute("??????????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("????????",sved_ipp));
//        attributes.save(new Attribute("????????1100",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????7",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????387_2??????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????284",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????",sved_ipp));
//        attributes.save(new Attribute("????????1200",sved_ipp));
//        attributes.save(new Attribute("????????6????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????",sved_ipp));
//        attributes.save(new Attribute("????????1300",sved_ipp));
//        attributes.save(new Attribute("????????????_??????",sved_ipp));
//        attributes.save(new Attribute("??????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("??????????",sved_ipp));
//        attributes.save(new Attribute("??????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("????????????",sved_ipp));
//        attributes.save(new Attribute("????????????????",nio_bs));
//        attributes.save(new Attribute("??????????",nio_bs));
//        attributes.save(new Attribute("??????????????",nio_bs));
//        attributes.save(new Attribute("??????????????????",nio_bs));
//        attributes.save(new Attribute("????????????",nio_bs));
//        attributes.save(new Attribute("????_????",nio_bs));
//        attributes.save(new Attribute("??????_??????",nio_bs));
//        attributes.save(new Attribute("??2_??????????",nio_bs));
//        attributes.save(new Attribute("??2_????????????????160",nio_bs));
//        attributes.save(new Attribute("??2_??????????????????????",nio_bs));
//        attributes.save(new Attribute("??2_??????????????????",nio_bs));
//        attributes.save(new Attribute("??2_????????????????????????",nio_bs));
//        attributes.save(new Attribute("????????????????????????",nio_bs));
//        attributes.save(new Attribute("????????????????????",nio_bs));
//        attributes.save(new Attribute("??????????????????????????",nio_bs));
//        attributes.save(new Attribute("??????????????",nio_bs));
//        attributes.save(new Attribute("??????????????",nio_bs));
//        attributes.save(new Attribute("??2_??????????????????",nio_bs));
//        attributes.save(new Attribute("??2_??????????????????????",nio_bs));
//        attributes.save(new Attribute("??2_??????????????",nio_bs));
//        attributes.save(new Attribute("??2_??????????????????",nio_bs));
//        attributes.save(new Attribute("??2_??????????????????",nio_bs));
//        attributes.save(new Attribute("??2_??????????????????",nio_bs));
//        attributes.save(new Attribute("????????????????????31_12",nio_bs));
//        attributes.save(new Attribute("????????????????????31_12",nio_bs));
//        attributes.save(new Attribute("????????????????",nio_ks));
//        attributes.save(new Attribute("??????????",nio_ks));
//        attributes.save(new Attribute("??????????????",nio_ks));
//        attributes.save(new Attribute("??????????????????",nio_ks));
//        attributes.save(new Attribute("????????????",nio_ks));
//        attributes.save(new Attribute("????_????",nio_ks));
//        attributes.save(new Attribute("??????_??????",nio_ks));
//        attributes.save(new Attribute("??3_??????????",nio_ks));
//        attributes.save(new Attribute("??3_??????????????????040",nio_ks));
//        attributes.save(new Attribute("??3_??????????????????070",nio_ks));
//        attributes.save(new Attribute("??3_????????????????",nio_ks));
//        attributes.save(new Attribute("??3_??????????????????",nio_ks));
//        attributes.save(new Attribute("??3_??????????????????",nio_ks));
//        attributes.save(new Attribute("??3_????????",nio_ks));
//        attributes.save(new Attribute("??3_??????????????????110",nio_ks));
//        attributes.save(new Attribute("??????????????",nio_ks));
//        attributes.save(new Attribute("??3_????????????????????????",nio_ks));
//        attributes.save(new Attribute("??3_??????????????????????",nio_ks));
//        attributes.save(new Attribute("??3_??????????????",nio_ks));
//        attributes.save(new Attribute("??3_??????????????????",nio_ks));
//        attributes.save(new Attribute("??3_C????????????????",nio_ks));
//        attributes.save(new Attribute("??3_??????????????????",nio_ks));
//        attributes.save(new Attribute("?????/??",nifl));
//        attributes.save(new Attribute("??????????",nifl));
//        attributes.save(new Attribute("??????????????????",nifl));
//        attributes.save(new Attribute("????",nifl));
//        attributes.save(new Attribute("????????????????????",nifl));
//        attributes.save(new Attribute("??????????????????",nifl));
//        attributes.save(new Attribute("????????????????????",nifl));
//        attributes.save(new Attribute("??????????????????????",nifl));
//        attributes.save(new Attribute("??????????????????",nifl));
//        attributes.save(new Attribute("????????????????????????????",nifl));
//        attributes.save(new Attribute("????????????????????",nifl));
//        attributes.save(new Attribute("????????????????????",nifl));
//        attributes.save(new Attribute("??????????????????????",nifl));
//        attributes.save(new Attribute("????????????????????????",nifl));
//        attributes.save(new Attribute("????????????????????????",nifl));
//        attributes.save(new Attribute("??????????????????????????",nifl));
//        attributes.save(new Attribute("??????????????????????????",nifl));
//        attributes.save(new Attribute("????????????????????????????",nifl));
//        attributes.save(new Attribute("????????????????",nifl));
//        attributes.save(new Attribute("????????????????_?????? ????",nifl));
//        attributes.save(new Attribute("??????????????",nifl));
//        attributes.save(new Attribute("??????????????????",nifl));
//        attributes.save(new Attribute("????????????_??????????",nifl));
//        attributes.save(new Attribute("????????????????????_??????",nifl));
//        attributes.save(new Attribute("????_????",nifl));
//        attributes.save(new Attribute("??????????????????????",nifl));
//        attributes.save(new Attribute("??????????????????2014",nifl));
//        attributes.save(new Attribute("??????????????????????????",nifl));
//        attributes.save(new Attribute("??????????????????????",nifl));
//        attributes.save(new Attribute("YEAR_NP",nifl));





//        dicts.save(new Dictionary(null, "schemaTypes", "schemaTypes", "xml", "xml"));
//        dicts.save(new Dictionary(null, "schemaTypes", "schemaTypes", "json", "json"));
//        dicts.save(new Dictionary(null, "schemaTypes", "schemaTypes", "text", "text"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "daily", "??????????????????"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "weekly", "??????????????????????"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "monthly", "????????????????????"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "quarterly", "??????????????????????????"));
//        dicts.save(new Dictionary(null, "schedulePeriodicities", "schedulePeriodicities", "yearly", "????????????????"));

//        users.save(new User("nkochubey","??????????????","??????????????","??????????????????????????","nkochubey@inbox.ru"));
//        users.save(new User("avgizov","??????????????","??????????????","????????????????","avgizov@inbox.ru"));
//        users.save(new User("maovchinnikov","????????????","????????????????????","??????????????????","maovchinnikov@inbox.ru"));
//        users.save(new User("amanchuk","????????????","????????????","","amanchuk@com3.tech"));
//
//        abonents.save(new Abonent("opn","?????? ??????", "???????????????????????????????????? ???????????????????????????? ?????????????? ?????????????????????? ???????????? ?????????????? ???????????????????????????? ???????????? ???????????? ???????????? ???? ?????????????????????? ?????????????????????? ?? ???????????? ???????????? ???????????? ?????????????? ???? ?????????????????? ?????????? ?????????????? ?? ????????????"));
//        abonents.save(new Abonent("fns",	"?????? ?????? ????????????", "???????????????????????????????????? ???????????????????????????? ?????????????? ?????? ?????????????????????????? ???????????????????????? ?????? ????????????"));
//        abonents.save(new Abonent("tensor", "????????????", "???? ????????????", ""));

//        Abonent fns = abonents.findByCode("fns").get();
//        schemas.save(new Schema(
//                fns,
//                "vo_svtorgsb",
//                "1.0.0",
//                "???????????????? ?? ???????????????????????? ?? ???????????????? ?????????????????? ???????????????? ???????????? ???? ???????????????????????? ?? ???????????????????? ???? ???????? ?? ???????????????? ?????????????????????? ?????????????????? ??????????",
//                "???????????????? ?? ???????????????????????? ?? ???????????????? ?????????????????? ???????????????? ???????????? ???? ???????????????????????? ?? ???????????????????? ???? ???????? ?? ???????????????? ?????????????????????? ?????????????????? ?????????? (???????????????? ?????????????????? ?????????????????????? ?????????????? ?????????????????????????? ????????????????, ?????????????????????? ?????????????? ?????????????????? ????????????), ???? ???????????????????????? ?? ???????????? ?? ?????????? ?? ???????????????? ?????????????????????? ?????????????????? ??????????, ???? ?????????? ?? ?????????????????? ???????????? ?????????????? ?????????????????? ???????????????? ????????????, ?????????? ?? ?????????????????? ?????????????????????????? ???????????????? (?????????????? ???????????????? ???? ???????????? ?????????????????? ??????????)")
//        );
//        schemas.save(new Schema(
//                fns,
//                "vo_actsvd",
//                "1.0.0",
//                "???????????????????? ???????????????? ???? ????????????????, ?????????????????? ???? ?????????? ?? ?????????????????? ???????????? ?? ?????????????????????????????? ??????????????, ?????????????????????? ???? ???????????????????????? ?? ??????????",
//                "???????????????????? ???????????????? (????????????????????) ???? ????????????????, ?????????????????? ???? ?????????? ?? ?????????????????? ???????????? ?? ?????????????????????????????? ??????????????, ?????????????????????? ???? ???????????????????????? ?? ??????????")
//        );
//        schemas.save(new Schema(
//                fns,
//                "deprmpsn",
//                "1.0.0",
//                "???????????????? ???? ???????????????? ???????????????????????????? ????????????????????????????????, ?????????????????????? ?????????????????? ?????????????? ??????????????????????????????",
//                "???????????????? ???? ???????????????? ???????????????????????????? ????????????????????????????????, ?????????????????????? ?????????????????? ?????????????? ??????????????????????????????")
//        );
//
//        Abonent opn = abonents.findByCode("opn").get();
//        schemas.save(new Schema(
//                opn,
//                "vo_akttorgsb",
//                "1.0.0",
//                "???????????????? ???? ???????????????? ?????????????????? ???????????????? ????????????",
//                "???????????????? ???? ???????????????? ?????????????????? ???????????????? ???????????? (???????????????? 2 ???????????? 4.2.1.2 ???????????????????? ????)")
//        );
//        schemas.save(new Schema(
//                opn,
//                "vo_nedimkad",
//                "1.0.0",
//                "???????????????? ???? ???????????????? ??????????????????????????????, ?????????????????? ???????? ???? ?????????????? ???????????????????????? ?????? ???? ?????????????????????? ??????????????????",
//                "???????????????? ???? ???????????????? ??????????????????????????????, ?????????????????? ???????? ???? ?????????????? ???????????????????????? ?????? ???? ?????????????????????? ??????????????????")
//        );
//        schemas.save(new Schema(
//                opn,
//                "bigcomp_msk",
//                "1.0.0",
//                "???????????????? ?? ???????????????? ?????????????????????? ???????????? ????????????, ?????????????????????? ?????? ???????????????????????? ???????? ???????????????????????????? ????????????????",
//                "???????????????? ?? ???????????????? ?????????????????????? ???????????? ????????????, ?????????????????????? ?????? ???????????????????????? ???????? ???????????????????????????? ????????????????")
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
