package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService{
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(vendor -> {
                    VendorDTO vendorDTO=vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl("/api/v1/vendors/"+vendor.getId());
                    return vendorDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id).map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
//        return vendorMapper.vendorToVendorDTO(vendorRepository.findById(id));
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor=vendorMapper.vendorDTOToVendor(vendorDTO);
        Vendor savedVendor=vendorRepository.save(vendor);
        VendorDTO vendorDTO1=vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTO1.setVendorUrl("/api/v1/vendors/"+savedVendor.getId());
        return vendorDTO1;
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor=vendorMapper.vendorDTOToVendor(vendorDTO);
       vendor.setId(id);
       Vendor savedVendor=vendorRepository.save(vendor);
       VendorDTO vendorDTO1=vendorMapper.vendorToVendorDTO(savedVendor);
       vendorDTO1.setVendorUrl("/api/v1/vendors/"+savedVendor.getId());
       return vendorDTO1;
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor ->
        {
            if(vendorDTO.getName()!=null)
            {
                vendor.setName(vendorDTO.getName());
            }
            VendorDTO vendorDTO1=vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO1.setVendorUrl("/api/v1/vendors"+vendor.getId());
            return vendorDTO1;
        }).orElseThrow(ResourceNotFoundException::new);
    }


}
