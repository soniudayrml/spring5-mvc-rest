package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE= Mappers.getMapper(VendorMapper.class);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);

    VendorDTO vendorToVendorDTO(Vendor vendor);

//    VendorDTO vendorToVendorDTO(Optional<Vendor> byId);
}
