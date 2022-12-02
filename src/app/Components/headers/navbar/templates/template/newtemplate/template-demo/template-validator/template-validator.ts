import { Injectable } from '@angular/core';
import { AbstractControl, FormArray, ValidatorFn } from '@angular/forms';

@Injectable({ providedIn: 'root' })
export class TemplateValidations {
    public RunValidation(): ValidatorFn {
        return (control: AbstractControl) => {
            this.validateCategoryControls(control);
            return null;
        }

        /*return (control: AbstractControl) => {

            const categoryControl = (control.get('category') as FormArray);
            console.log('categoryControl: ', categoryControl.length);

            const initialCategoryValue = categoryControl?.value[0].name;
            console.log('initialCategoryValue: ', initialCategoryValue);

            categoryControl.value?.forEach((category: any, index: number) => {
                console.log('category value: ', category.name);
                if (index > 0) {
                    if (initialCategoryValue == category.name) {
                        console.log('Error at category index: ', index);
                        categoryControl.at(index).setErrors({ categoryError: true });
                        return;
                    } else {
                        let initialSubCategoryValue = control?.value['category'][0]['subcategory'][0].subcategoryname;
                        console.log('initialSubCategoryValue: ', initialSubCategoryValue);

                        control?.value['category'][index]['subcategory'].forEach((subCategory: any, subCategoryIndex: number) => {

                            console.log('subcategoryname value: ', subCategory.subcategoryname);
                            if (initialSubCategoryValue == subCategory.subcategoryname) {
                                console.log('Error at subCategory index: ', subCategoryIndex);
                                (categoryControl?.at(index)?.get('subcategory') as FormArray).at(subCategoryIndex).setErrors({ subCategoryError: true });
                                return;
                            } else {
                                // run parameter validations
                                let initialParameterValue = control?.value['category'][0]['subcategory'][0]['parameter'][0].parametername;
                                console.log('initialParameterValue: ', initialParameterValue);
                                control?.value['category'][index]['subcategory'][subCategoryIndex]['parameter'].forEach((parameter: any, parameterIndex: number) => {
                                    console.log('parametername value: ', parameter.parametername);

                                    if (initialParameterValue == parameter.parametername) {
                                        console.log('Error at parameter index: ', parameterIndex);
                                        ((categoryControl?.at(index)?.get('subcategory') as FormArray).at(subCategoryIndex).get('parameter') as FormArray).at(parameterIndex).setErrors({ parameterError: true });
                                        return;
                                    }
                                });
                            }
                        });
                    }
                }
            });
            return null;
        };*/
    }

    private validateCategoryControls(control: any) {

        const categoryControl = (control.get('category') as FormArray);
        console.log('categoryControl: ', categoryControl.length);

        const initialCategoryValue = categoryControl?.value[0].name;
        console.log('initialCategoryValue: ', initialCategoryValue);

        categoryControl.value?.forEach((category: any, index: number) => {
            console.log('category value: ', category.name);
            if (index > 0) {
                if (initialCategoryValue == category.name) {
                    console.log('Error at category index: ', index);
                    categoryControl.at(index).setErrors({ categoryError: true });
                    return;
                } else {
                    return this.validateSubCategoryControls(categoryControl, control, index);
                }
            }
        });
    }

    private validateSubCategoryControls(categoryControl: any, control: any, index: number) {
        let initialSubCategoryValue = control?.value['category'][0]['subcategory'][0].subcategoryname;
        console.log('initialSubCategoryValue: ', initialSubCategoryValue);

        control?.value['category'][index]['subcategory'].forEach((subCategory: any, subCategoryIndex: number) => {

            console.log('subcategoryname value: ', subCategory.subcategoryname);
            if (initialSubCategoryValue == subCategory.subcategoryname) {
                console.log('Error at subCategory index: ', subCategoryIndex);
                (categoryControl?.at(index)?.get('subcategory') as FormArray).at(subCategoryIndex).setErrors({ subCategoryError: true });
                return;
            } else {
                return this.validateParameterControls(categoryControl, control, index, subCategoryIndex);
            }
        })
    }

    private validateParameterControls(categoryControl: any, control: any, index: number, subCategoryIndex: number) {
        let initialParameterValue = control?.value['category'][0]['subcategory'][0]['parameter'][0].parametername;
        console.log('initialParameterValue: ', initialParameterValue);
        control?.value['category'][index]['subcategory'][subCategoryIndex]['parameter'].forEach((parameter: any, parameterIndex: number) => {
            console.log('parametername value: ', parameter.parametername);

            if (initialParameterValue == parameter.parametername) {
                console.log('Error at parameter index: ', parameterIndex);
                ((categoryControl?.at(index)?.get('subcategory') as FormArray).at(subCategoryIndex).get('parameter') as FormArray).at(parameterIndex).setErrors({ parameterError: true });
                return;
            }
        });
    }

    private validateCategory(control: any) {
        const categoryControl = (control.get('category') as FormArray);
        var foundDuplicateName = categoryControl.value.find((row: any, rowIindex: number) =>{
            return categoryControl.value.find((data: any, i: number)=> data.name === row.name && rowIindex !== i )
        });

        console.log('foundDuplicateName: ', foundDuplicateName)
    }
}