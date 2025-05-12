package co.edu.unbosque.PayrollAPI.configuration;

import co.edu.unbosque.PayrollAPI.dto.regular.ConceptoNominaDTO;
import co.edu.unbosque.PayrollAPI.dto.regular.EmpleadoDTO;
import co.edu.unbosque.PayrollAPI.dto.regular.NivelRiesgoArlDTO;
import co.edu.unbosque.PayrollAPI.dto.regular.TipoContratoDTO;
import co.edu.unbosque.PayrollAPI.entity.Empleado;
import co.edu.unbosque.PayrollAPI.entity.NivelRiesgoArl;
import co.edu.unbosque.PayrollAPI.entity.TipoContrato;
import co.edu.unbosque.PayrollAPI.projection.ConceptoHoraExtraProjection;
import co.edu.unbosque.PayrollAPI.projection.ConceptoTipoConceptoProjection;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        cargarMapeos(modelMapper);
        return modelMapper;
    }


    private void cargarMapeos(ModelMapper modelMapper){

        modelMapper.typeMap(Empleado.class, EmpleadoDTO.class).addMappings(mapper -> {
            mapper.map(Empleado::getNivelRiesgo, EmpleadoDTO::setNivelRiesgoDTO);
            mapper.map(Empleado::getTipoContrato, EmpleadoDTO::setTipoContratoDTO);
        });


        modelMapper.typeMap(NivelRiesgoArl.class, NivelRiesgoArlDTO.class).addMappings(mapper -> {
            mapper.map(NivelRiesgoArl::getIdNivel, NivelRiesgoArlDTO::setIdNivel);
            mapper.map(NivelRiesgoArl::getNombre, NivelRiesgoArlDTO::setNombre);
            mapper.map(NivelRiesgoArl::getPorcentaje, NivelRiesgoArlDTO::setPorcentaje);
        });

        modelMapper.typeMap(TipoContrato.class, TipoContratoDTO.class).addMappings(mapper -> {
            mapper.map(TipoContrato::getIdTipoContrato, TipoContratoDTO::setIdTipoContrato);
            mapper.map(TipoContrato::getNombre, TipoContratoDTO::setNombre);
        });

        modelMapper
                .typeMap(ConceptoTipoConceptoProjection.class, ConceptoNominaDTO.class)
                .addMapping(ConceptoTipoConceptoProjection::getIdConcepto, ConceptoNominaDTO::setIdConcepto);

        modelMapper
                .typeMap(ConceptoHoraExtraProjection.class, ConceptoNominaDTO.class)
                .addMapping(ConceptoHoraExtraProjection::getIdConcepto, ConceptoNominaDTO::setIdConcepto)
                .addMapping(ConceptoHoraExtraProjection::getNombre, ConceptoNominaDTO::setNombre)
                .addMapping(ConceptoHoraExtraProjection::getPorcentaje, ConceptoNominaDTO::setPorcentaje)
                .addMapping(ConceptoHoraExtraProjection::getDescripcion, ConceptoNominaDTO::setDescripcion)
                .addMapping(ConceptoHoraExtraProjection::getActivo, ConceptoNominaDTO::setActivo);


    }

}
