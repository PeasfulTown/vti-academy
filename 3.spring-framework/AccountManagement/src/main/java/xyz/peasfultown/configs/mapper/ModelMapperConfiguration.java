package xyz.peasfultown.configs.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.peasfultown.dtos.AccountDTO;
import xyz.peasfultown.models.Account;
import xyz.peasfultown.models.PositionName;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        Converter<PositionName, String> posNameEnumToString = ctx -> ctx.getSource() != null ?
//                ctx.getSource().toString() : null;
        mapper.addMappings(new PropertyMap<Account, AccountDTO>() {
            @Override
            protected void configure() {
                map().setDepartmentName(source.getDepartment().getName());
//                map().setPositionName(source.getPosition().getName().toString());
            }
        });

        return mapper;
    }
}
