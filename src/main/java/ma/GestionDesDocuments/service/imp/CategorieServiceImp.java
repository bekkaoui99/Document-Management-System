package ma.GestionDesDocuments.service.imp;

import ma.GestionDesDocuments.dto.CategorieDto;
import ma.GestionDesDocuments.entities.Categorie;
import ma.GestionDesDocuments.exception.ResourceNotFoundException;
import ma.GestionDesDocuments.repositories.CategorieRepositorie;
import ma.GestionDesDocuments.service.CategorieService;
import ma.GestionDesDocuments.state.LogConvertToPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieServiceImp implements CategorieService {

    @Autowired
    private CategorieRepositorie categorieRepositorie;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategorieDto create(CategorieDto categorieDto) {
        Categorie createdCategorie = categorieRepositorie.save(modelMapper.map(categorieDto, Categorie.class));
        return modelMapper.map(createdCategorie , CategorieDto.class);
    }

    @Override
    public List<CategorieDto> getAllCategorie() {
        return categorieRepositorie
                .findAll()
                .stream()
                .map(categorie -> modelMapper.map(categorie , CategorieDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LogConvertToPage<CategorieDto> getAllCategorie(Pageable pageable) {
        List<CategorieDto> all = categorieRepositorie.findAll(pageable)
                .getContent()
                .stream()
                .map(categorie -> modelMapper.map(categorie, CategorieDto.class))
                .collect(Collectors.toList());

        LogConvertToPage<CategorieDto> logConvertToPage = new LogConvertToPage<>();
        logConvertToPage.setLogConverterDtos(all);
        logConvertToPage.setPageIndex(categorieRepositorie.findAll(pageable).getNumber());
        logConvertToPage.setTotalPages(categorieRepositorie.findAll(pageable).getTotalPages());
        logConvertToPage.setPageSize(categorieRepositorie.findAll(pageable).getSize());
        return logConvertToPage;
    }

    @Override
    public LogConvertToPage getAllCategorieByTitre(String titre, Pageable pageable) {
        List<CategorieDto> all = categorieRepositorie.findByTitreContaining(titre , pageable)
                .getContent()
                .stream()
                .map(categorie -> modelMapper.map(categorie, CategorieDto.class))
                .collect(Collectors.toList());

        LogConvertToPage<CategorieDto> logConvertToPage = new LogConvertToPage<>();
        logConvertToPage.setLogConverterDtos(all);
        logConvertToPage.setPageIndex(categorieRepositorie.findAll(pageable).getNumber());
        logConvertToPage.setTotalPages(categorieRepositorie.findAll(pageable).getTotalPages());
        logConvertToPage.setPageSize(categorieRepositorie.findAll(pageable).getSize());
        return logConvertToPage;
    }

    @Override
    public CategorieDto getCategorieByid(int id) {
        Categorie categorie = categorieRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie"));
        return modelMapper.map(categorie , CategorieDto.class);
    }

    @Override
    public List<CategorieDto> getCategorieByTitre(String titre) {

        return   categorieRepositorie.findByTitreContaining(titre)
                .stream()
                .map(categorie -> modelMapper.map(categorie , CategorieDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        Categorie categorie = categorieRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie"));
        categorieRepositorie.delete(categorie);
    }

    @Override
    public CategorieDto update(int id, CategorieDto categorieDto) {
        Categorie categorie = categorieRepositorie
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie"));
        categorie.setId(id);
        return modelMapper.map(categorieRepositorie.save(categorie) , CategorieDto.class);
    }
}
