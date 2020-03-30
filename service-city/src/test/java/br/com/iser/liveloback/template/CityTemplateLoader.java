// package br.com.iser.liveloback.template;
//
// import br.com.iser.liveloback.constant.TestConstant;
// import br.com.iser.liveloback.model.City;
// import br.com.iser.liveloback.model.dto.CityDTO;
// import br.com.six2six.fixturefactory.Fixture;
// import br.com.six2six.fixturefactory.Rule;
//
// public class CityTemplateLoader implements TemplateLoader {
//
// @Override
// public void load() {
//
// Fixture.of(City.class).addTemplate(TestConstant.CITY_ENTITY.value(), new Rule() {
// {
// add("idCity", random(Integer.class, range(1, 200)));
// add("state", random("RS", "RJ", "PR"));
// add("name", random("Rio Grande", "Rio de Janeiro", "Curitiba"));
// }
// });
//
// Fixture.of(CityDTO.class).addTemplate(TestConstant.CITY_DTO.value(), new Rule() {
// {
// add("idCity", random(Integer.class, range(1, 200)));
// add("state", random("RS", "RJ", "PR", "SP"));
// add("name", random("Rio Grande", "Rio de Janeiro", "Curitiba", "Campinas"));
// }
// });
// }
// };
