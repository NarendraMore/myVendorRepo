import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { TemplatebuilderService } from 'src/app/templatebuilder.service';
import { CombineCategoryDialogComponent } from './combine-category-dialog/combine-category-dialog.component';
import { Category, ProductDEMO } from './product-interface';
import { TemplateValidations } from './template-validator/template-validator';

@Component({
  selector: 'app-template-demo',
  templateUrl: './template-demo.component.html',
  styleUrls: ['./template-demo.component.css'],
})
export class TemplateDemoComponent implements OnInit {
  productsData!: ProductDEMO[];
  categories!: Category[];
  productsForm!: FormGroup;

  categoriesData = [
    {
      name: 'Category',
      id: 'c1',
      states: [
        { name: 'Organisation Profile' },
        { name: 'IT Infrastructure Services' },
        { name: 'Service level Agreement' },
        { name: 'Transition Approach' },
        { name: 'Adherence to Regulations/Standard and Compliance' },
        { name: 'Continuous Improvement' },
        { name: 'Commercial Evaluation' },
        { name: 'Organisation Profile' },
        { name: 'Experience and Customer References' },
        { name: 'Commercial Evaluation (Security - 5 Year)' },
        { name: 'Commercial Evaluation (Infra- 5 Year)' },
        { name: 'Security Services Offerings (On Going) & IT SOC Service Offering (One Time)' }
      ]
    },
    {
      name: 'Subcategory',
      id: 'sc1',
      states: [
        { name: 'Servers support' },
        { name: 'Network services support' },
        { name: 'For O365/M365 services support' },
        { name: 'For Cloud Infrastructure support' },
        { name: 'Proposed Geographical distribution of Manpower' },
        { name: 'Compliant to Clients current technology landscape' },
        { name: 'Approach and Methodology' },
        { name: 'Exceptions' },
        { name: 'Offering NG SIEM' },
        { name: 'Offering User and Entity Behavior Analytics (UEBA )' },
        { name: 'Offering Threat prevention/detection services' },
      ]
    },
    {
      name: 'Subcategory Two',
      id: 'sc2',
      states: [
        { name: 'Subcategory two value 1' },
        { name: 'Subcategory two value 2' }
      ]
    },
    {
      name: 'Subcategory Three',
      id: 'sc3',
      states: [
        { name: 'Subcategory three value 1' },
        { name: 'Subcategory three value 2' }
      ]
    },
    {
      name: 'Parameter',
      code: 'p1',
      states: [
        { name: 'Service Desk' },
        { name: 'Incident management' },
        { name: 'Problem management' },
        { name: 'Asset and configuration management (IMAC)' },
        { name: 'Availability Management' },
        { name: 'Monitoring Management' },
        { name: 'Capacity Management' },
        { name: 'Backup Management' },
        { name: 'Security Management' },
        { name: 'Customer Relationship Management' },
        { name: 'Vendor Management' },
        { name: 'Disaster Recovery Support' }
      ]
    }
  ];

  constructor(
    private templateService: TemplatebuilderService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private templateValidations: TemplateValidations) { }

  ngOnInit(): void {
    this.getProductsData();
    this.initForm();
    this.getCategoryControls().valueChanges.subscribe(console.log);

    this.subscribeCombineOperation();

  }

  private initForm(): void {
    this.productsForm = new FormGroup({
      id: new FormControl(''),
      category: new FormArray([
        new FormGroup({
          name: new FormControl(''),
          weightage: new FormControl(100),
          subcategory: new FormArray([
            new FormGroup({
              subcategoryname: new FormControl(''),
              weightage: new FormControl(100),
              subcategoryTwo: new FormArray([
                new FormGroup({
                  subcategoryname: new FormControl(''),
                  weightage: new FormControl(100),
                  subcategoryThree: new FormArray([
                    new FormGroup({
                      subcategoryname: new FormControl(''),
                      weightage: new FormControl(100),
                      parameter: new FormArray([
                        new FormGroup({
                          parametername: new FormControl(''),
                          weightage: new FormControl(100),
                          maxschore: new FormControl(''),
                          schoringcriteria: new FormArray([
                            new FormGroup({
                              criteriaValue: new FormControl('')
                            })
                          ])
                        })
                      ])
                    })
                  ])
                })
              ]),
            }),
          ])
        }),
      ]),
    }, /*{
      validators: [this.templateValidations.RunValidation()],
      updateOn: 'change',
    }*/);
  }

  getProductsData(): void {
    this.templateService
      .getProduct()
      .subscribe((response: any) => { this.productsData = response; this.categories = this.productsData[0].category; });
  }

  getCategoryControls(): FormArray {
    return (this.productsForm.get('category') as FormArray)
  }

  getSubCategoryControls(index: number): FormArray {
    return this.getCategoryControls().at(index).get('subcategory') as FormArray
  }

  getSubCategoryTwoControls(categoryIndex: number, index: number): FormArray {
    return this.getSubCategoryControls(categoryIndex).at(index).get('subcategoryTwo') as FormArray
  }

  getSubCategoryThreeControls(categoryIndex: number, subCategoryIndex: number, index: number): FormArray {
    return this.getSubCategoryTwoControls(categoryIndex, subCategoryIndex).at(index).get('subcategoryThree') as FormArray
  }

  getParameterControls(categoryIndex: number, subCategoryIndex: number, subCategoryTwoIndex: number, index: number): FormArray {
    return this.getSubCategoryThreeControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex).at(index).get('parameter') as FormArray;
  }

  getScoringCriteriaControls(categoryIndex: number, subCategoryIndex: number, subCategoryTwoIndex: number, subCategoryThreeIndex: number, index: number): FormArray {
    return this.getParameterControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex, subCategoryThreeIndex).at(index).get('schoringcriteria') as FormArray;
  }

  addCategory(): void {
    let rowCount: any = prompt("How many categories you want to create ?");

    const calculateWeightage = Math.floor(100 / ((this.productsForm.get("category") as FormArray).length + rowCount))
    console.log(calculateWeightage);
    let i = 0;
    while (i < rowCount) {
      (this.productsForm.get("category") as FormArray).push(new FormGroup({
        name: new FormControl(''),
        weightage: new FormControl([calculateWeightage]),
        subcategory: new FormArray([
          new FormGroup({
            subcategoryname: new FormControl(''),
            weightage: new FormControl(100),
            subcategoryTwo: new FormArray([
              new FormGroup({
                subcategoryname: new FormControl(''),
                weightage: new FormControl(100),
                subcategoryThree: new FormArray([
                  new FormGroup({
                    subcategoryname: new FormControl(''),
                    weightage: new FormControl(100),
                    parameter: new FormArray([
                      new FormGroup({
                        parametername: new FormControl(''),
                        weightage: new FormControl(100),
                        maxschore: new FormControl(''),
                        schoringcriteria: new FormArray([
                          new FormGroup({
                            criteriaValue: new FormControl('')
                          })
                        ])
                      })
                    ])
                  })
                ])
              })
            ]),
          }),
        ])
      }));

      i++;
    }

    this.calculateWeightage((this.productsForm.get("category") as FormArray));
  }

  removeCategory(index: number): void {
    (this.productsForm.get("category") as FormArray).removeAt(index);
    this.calculateWeightage((this.productsForm.get("category") as FormArray));
  }

  addSubCategory(categoryIndex: number): void {
    let rowCount: any = prompt("How many Sub categories you want to create ?");

    let i = 0;
    while (i < rowCount) {
      this.getSubCategoryControls(categoryIndex).push(new FormGroup({
        subcategoryname: new FormControl(''),
        weightage: new FormControl(100),
        subcategoryTwo: new FormArray([
          new FormGroup({
            subcategoryname: new FormControl(''),
            weightage: new FormControl(100),
            subcategoryThree: new FormArray([
              new FormGroup({
                subcategoryname: new FormControl(''),
                weightage: new FormControl(100),
                parameter: new FormArray([
                  new FormGroup({
                    parametername: new FormControl(''),
                    weightage: new FormControl(100),
                    maxschore: new FormControl(''),
                    schoringcriteria: new FormArray([
                      new FormGroup({
                        criteriaValue: new FormControl('')
                      })
                    ])
                  })
                ])
              })
            ])
          })
        ]),
      }));
      i++;
    }

    this.calculateWeightage(this.getSubCategoryControls(categoryIndex));
  }

  removeSubCategory(categoryIndex: number, index: number): void {
    (this.getCategoryControls().at(categoryIndex).get('subcategory') as FormArray).removeAt(index);
    this.calculateWeightage(this.getSubCategoryControls(categoryIndex));
  }

  addSubCategoryTwo(categoryIndex: number, subCategoryIndex: number): void {
    let rowCount: any = prompt("How many Sub categories you want to create ?");

    let i = 0;
    while (i < rowCount) {
      this.getSubCategoryTwoControls(categoryIndex, subCategoryIndex).push(new FormGroup({
        subcategoryname: new FormControl(),
        weightage: new FormControl(100),
        subcategoryThree: new FormArray([
          new FormGroup({
            subcategoryname: new FormControl(''),
            weightage: new FormControl(100),
            parameter: new FormArray([
              new FormGroup({
                parametername: new FormControl(''),
                weightage: new FormControl(100),
                maxschore: new FormControl(''),
                schoringcriteria: new FormArray([
                  new FormGroup({
                    criteriaValue: new FormControl('')
                  })
                ])
              })
            ])
          })
        ])
      }));
      i++;
    }

    this.calculateWeightage(this.getSubCategoryTwoControls(categoryIndex, subCategoryIndex));
  }

  removeSubCategoryTwo(categoryIndex: number, subCategoryIndex: number, index: number): void {
    (this.getSubCategoryTwoControls(categoryIndex, subCategoryIndex) as FormArray).removeAt(index);
    this.calculateWeightage(this.getSubCategoryTwoControls(categoryIndex, subCategoryIndex));
  }

  addSubCategoryThree(categoryIndex: number, subCategoryIndex: number, subCategoryTwoIndex: number): void {
    let rowCount: any = prompt("How many Sub categories you want to create ?");

    let i = 0;
    while (i < rowCount) {
      this.getSubCategoryThreeControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex).push(new FormGroup({
        subcategoryname: new FormControl(),
        weightage: new FormControl(100),
        parameter: new FormArray([
          new FormGroup({
            parametername: new FormControl(''),
            weightage: new FormControl(100),
            maxschore: new FormControl(''),
            schoringcriteria: new FormArray([
              new FormGroup({
                criteriaValue: new FormControl('')
              })
            ])
          })
        ])
      }));
      i++;
    }

    this.calculateWeightage(this.getSubCategoryThreeControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex));
  }

  removeSubCategoryThree(categoryIndex: number, subCategoryIndex: number, subCategoryTwoIndex: number, index: number): void {
    (this.getSubCategoryThreeControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex) as FormArray).removeAt(index);
    this.calculateWeightage(this.getSubCategoryThreeControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex));
  }

  addParameter(categoryIndex: number, subCategoryIndex: number, subCategoryTwoIndex: number, subCategoryThreeIndex: number): void {
    let rowCount: any = prompt("How many Parameters you want to create ?");

    let i = 0;
    while (i < rowCount) {
      this.getParameterControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex, subCategoryThreeIndex).push(new FormGroup({
        parametername: new FormControl(),
        weightage: new FormControl(100),
        maxschore: new FormControl(''),
        schoringcriteria: new FormArray([
          new FormGroup({
            criteriaValue: new FormControl('')
          })
        ])
      }));

      i++;
    }

    this.calculateWeightage(this.getParameterControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex, subCategoryThreeIndex));
  }

  removeParameter(categoryIndex: number, subCategoryIndex: number, subCategoryTwoIndex: number, subCategoryThreeIndex: number, index: number): void {
    this.getParameterControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex, subCategoryThreeIndex).removeAt(index);
    this.calculateWeightage(this.getParameterControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex, subCategoryThreeIndex));
  }

  addScoringCriteria(categoryIndex: number, subCategoryIndex: number, subCategoryTwoIndex: number, subCategoryThreeIndex: number, parameterIndex: number): void {

    let rowCount: any = prompt("How many scoring criteria you want to create ?");

    let i = 0;
    while (i < rowCount) {
      this.getScoringCriteriaControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex, subCategoryThreeIndex, parameterIndex).push(new FormGroup({
            criteriaValue: new FormControl('')
          }));
      i++;
    }
  }

  removeScoringCriteria(categoryIndex: number, subCategoryIndex: number, subCategoryTwoIndex: number, subCategoryThreeIndex: number, parameterIndex: number, index: number): void {
    this.getScoringCriteriaControls(categoryIndex, subCategoryIndex, subCategoryTwoIndex, subCategoryThreeIndex, parameterIndex).removeAt(index);
  }

  private calculateWeightage(list: FormArray): void {
    const newWeightage = Math.floor(100 / list.length);
    list.controls.forEach((control: any) => {
      (control as FormGroup).patchValue({
        weightage: newWeightage
      })
    })
  }

  private prepareSubCategoryTwoControl(subcategory: any, data?: any) {
    let subcategoryTwoControls = new FormArray([]);
    subcategory.subcategoryTwo.forEach((subcategoryTwo: any) => {
      subcategoryTwoControls.push(new FormGroup({
        subcategoryname: new FormControl(subcategoryTwo.subcategoryname),
        weightage: new FormControl(100),
        subcategoryThree: this.prepareSubCategoryThreeControl(subcategoryTwo)
      }));
    });
    this.calculateWeightage(subcategoryTwoControls);
    return subcategoryTwoControls;
  }

  private prepareSubCategoryThreeControl(subcategoryTwo: any, data?: any) {
    let subcategoryThreeControls = new FormArray([]);
    subcategoryTwo.subcategoryThree.forEach((subcategoryThree: any) => {
      console.log('subcategoryThree.subcategoryname: ', subcategoryThree.subcategoryname)
      subcategoryThreeControls.push(new FormGroup({
        subcategoryname: new FormControl(subcategoryThree.subcategoryname),
        weightage: new FormControl(100),
        parameter: this.prepareParameterControl(subcategoryThree)
      }));
    });
    this.calculateWeightage(subcategoryThreeControls);
    return subcategoryThreeControls;
  }

  private prepareParameterControl(subcategoryThree: any, data?: any) {
    let parameterControls = new FormArray([]);
    subcategoryThree.parameter.forEach((parameter: any) => {
      parameterControls.push(new FormGroup({
        parametername: new FormControl(parameter.parametername),
        weightage: new FormControl(100),
        maxschore: new FormControl(''),
        schoringcriteria: this.prepareScoringCriteriaControl(parameter)
      }));
    });
    this.calculateWeightage(parameterControls);
    return parameterControls;
  }

  private prepareScoringCriteriaControl(parameter: any) {
    let scoringCriteriaControls = new FormArray([]);
    parameter.schoringcriteria.forEach((schoringcriteria: any) => {
      scoringCriteriaControls.push(new FormGroup({
        criteriaValue: new FormControl(schoringcriteria.criteriaValue)
      }));
    });
    return scoringCriteriaControls;
  }


  openDialog(data: any) {
    this.dialog.open(CombineCategoryDialogComponent, {
      data: {
        input: data
      },
    });
  }

  initCombine(categoryIndex: number, operation: string, subCategoryIndex?: any, subCategoryTwoIndex?: any, subCategoryThreeIndex?: any, parameterIndex?: any): void {
    let data = this.getCategoryControls().value;
    let newData: any;

    switch (operation) {
      case 'category':
        newData = data.filter((row: any, index: number) => {
          if (row.name != data[categoryIndex].name) {
            return row.name;
          }
        });
        break;
      case 'subCategory':
        newData = this.getDropdownSubCategoryList(data[categoryIndex].subcategory[subCategoryIndex].subcategoryname, data, operation);
        break;
      case 'subCategoryTwo':
        newData = this.getDropdownSubCategoryList(data[categoryIndex].subcategory[subCategoryIndex].subcategoryTwo[subCategoryTwoIndex].subcategoryname, data, operation);
        break;
      case 'subCategoryThree':
        newData = this.getDropdownSubCategoryList(data[categoryIndex].subcategory[subCategoryIndex].subcategoryTwo[subCategoryTwoIndex].subcategoryThree[subCategoryThreeIndex].subcategoryname, data, operation);
        break;
      case 'parameter':
        newData = this.getDropdownSubCategoryList(data[categoryIndex].subcategory[subCategoryIndex].subcategoryTwo[subCategoryTwoIndex].subcategoryThree[subCategoryThreeIndex].subcategoryname, data, 'subCategoryThree');
        break;
    }


    this.openDialog({
      newData: newData,
      originalData: data,
      categoryIndex: categoryIndex,
      subCategoryIndex: subCategoryIndex,
      subCategoryTwoIndex: subCategoryTwoIndex,
      subCategoryThreeIndex: subCategoryThreeIndex, 
      parameterIndex: parameterIndex,
      operation: operation
    });
  }

  private getDropdownSubCategoryList(value: string, data: any, operation?: any): void {
    let temp: any = [];
    data.forEach((category: any) => {
      category.subcategory.forEach((subCategory: any) => {

        if (operation == 'subCategory') {
          if (subCategory.subcategoryname != value) {
            console.log('subCategory: ', subCategory)
            temp.push({ name: subCategory.subcategoryname });
          }
        } else {
          subCategory.subcategoryTwo.forEach((subCategoryTwo: any) => {
            if (operation == 'subCategoryTwo') {
              if (subCategoryTwo.subcategoryname != value) {
                console.log('subCategoryTwo: ', subCategoryTwo)
                temp.push({ name: subCategoryTwo.subcategoryname });
              }
            } else {
              subCategoryTwo.subcategoryThree.forEach((subCategoryThree: any) => {
                if (operation == 'subCategoryThree') {
                  if (subCategoryThree.subcategoryname != value) {
                    console.log('subCategoryThree: ', subCategoryThree)
                    temp.push({ name: subCategoryThree.subcategoryname });
                  }
                } else {
                  subCategoryThree.parameter.forEach((parameter: any) => {
                    if (operation == 'parameter') {
                      if (parameter.parametername != value) {
                        console.log('parameter: ', parameter)
                        temp.push({ name: parameter.parametername });
                      }
                    }
                  });
                }
              });
            }
          });
        }

      });
    });
    return temp;
  }

  private subscribeCombineOperation() {
    this.templateService.combineFormDataSubscriber$.subscribe((data: any) => {
      console.log('subscription data: ', data)
      switch (data.operation) {
        case 'category':
          this.combineCategory(data);
          break;
        case 'subCategory':
          this.combineSubCategory(data);
          break;
        case 'subCategoryTwo':
          this.combineSubCategoryTwo(data);
          break;
        case 'subCategoryThree':
          this.combineSubCategoryThree(data);
          break;
        case 'parameter':
          this.combineParameter(data);
          break;

      }
    });
  }

  private combineCategory(data: any): void {
    console.log('combine into index: ', data.categoryIndex);
    this.getSubCategoryControls(data.categoryIndex).value.forEach((subcategory: any, subCategoryIndex: number) => {

      this.getSubCategoryControls(data.selectedIndex).push(new FormGroup({
        subcategoryname: new FormControl(subcategory.subcategoryname),
        weightage: new FormControl(100),
        subcategoryTwo: this.prepareSubCategoryTwoControl(subcategory, {
          categoryIndex: data.categoryIndex,
          subCategoryIndex: subCategoryIndex
        })
      }));

      this.calculateWeightage((this.getSubCategoryControls(data.selectedIndex)));
    });

    this.removeCategory(data.categoryIndex);
    this.dialog.closeAll();
  }


  private combineSubCategory(data: any): void {

    console.log('combine into index: ', data.categoryIndex);
    this.getSubCategoryTwoControls(data.categoryIndex, data.subCategoryIndex).value.forEach((subcategoryTwo: any, subcategoryTwoIndex: number) => {

      this.getSubCategoryTwoControls(data.selectedCategoryIndex, data.selectedIndex).push(new FormGroup({
        subcategoryname: new FormControl(subcategoryTwo.subcategoryname),
        weightage: new FormControl(100),
        subcategoryThree: this.prepareSubCategoryThreeControl(subcategoryTwo, {
          categoryIndex: data.categoryIndex,
          subcategoryTwoIndex: subcategoryTwoIndex
        })
      }));

      this.calculateWeightage((this.getSubCategoryTwoControls(data.selectedCategoryIndex, data.selectedIndex)));
    });

    this.removeSubCategory(data.categoryIndex, data.subCategoryIndex);
    this.dialog.closeAll();
  }

  private combineSubCategoryTwo(data: any): void {

    console.log('combine into index: ', data.categoryIndex);
    this.getSubCategoryThreeControls(data.categoryIndex, data.subCategoryIndex, data.subCategoryTwoIndex).value.forEach((subcategoryThree: any, subcategoryThreeIndex: number) => {

      this.getSubCategoryThreeControls(data.selectedCategoryIndex, data.selectedSubCategoryIndex, data.selectedIndex).push(new FormGroup({
        subcategoryname: new FormControl(subcategoryThree.subcategoryname),
        weightage: new FormControl(100),
        parameter: this.prepareParameterControl(subcategoryThree, {
          categoryIndex: data.categoryIndex
        })
      }));

      this.calculateWeightage((this.getSubCategoryThreeControls(data.selectedCategoryIndex, data.selectedSubCategoryIndex, data.selectedIndex)));
    });

    this.removeSubCategoryTwo(data.categoryIndex, data.subCategoryIndex, data.subCategoryTwoIndex);
    this.dialog.closeAll();
  }

  private combineSubCategoryThree(data: any): void {

    console.log('combine into index: ', data.categoryIndex);
    this.getParameterControls(data.categoryIndex, data.subCategoryIndex, data.subCategoryTwoIndex, data.subCategoryThreeIndex).value.forEach((parameter: any, parameterIndex: number) => {

      this.getParameterControls(data.selectedCategoryIndex, data.selectedSubCategoryIndex, data.selectedSubCategoryTwoIndex, data.selectedIndex).push(new FormGroup({
          parametername: new FormControl(parameter.parametername),
          weightage: new FormControl(100),
          maxschore: new FormControl(parameter.maxschore),
          schoringcriteria: this.prepareScoringCriteriaControl(parameter)
        }))

      });

      this.calculateWeightage((this.getParameterControls(data.selectedCategoryIndex, data.selectedSubCategoryIndex, data.selectedSubCategoryTwoIndex, data.selectedIndex)));

    this.removeSubCategoryThree(data.categoryIndex, data.subCategoryIndex, data.subCategoryTwoIndex, data.subCategoryThreeIndex);
    this.dialog.closeAll();
  }

  private combineParameter(data: any): void {
    
    let parameterControl = this.getParameterControls(data.categoryIndex, data.subCategoryIndex, data.subCategoryTwoIndex, data.subCategoryThreeIndex).at(data.parameterIndex);

    this.getParameterControls(data.selectedCategoryIndex, data.selectedSubCategoryIndex, data.selectedSubCategoryTwoIndex, data.selectedSubCategoryThreeIndex).push(new FormGroup({
      parametername: new FormControl(parameterControl.value.parametername),
      weightage: new FormControl(100),
      maxschore: new FormControl(parameterControl.value.maxschore),
      schoringcriteria: this.prepareScoringCriteriaControl(parameterControl.value)
    }))

      this.calculateWeightage(this.getParameterControls(data.selectedCategoryIndex, data.selectedSubCategoryIndex, data.selectedSubCategoryTwoIndex, data.selectedIndex));

      this.removeParameter(data.categoryIndex, data.subCategoryIndex, data.subCategoryTwoIndex, data.subCategoryThreeIndex, data.parameterIndex);
      this.dialog.closeAll();
  }

  onSave() {
    console.log(this.productsForm);
    console.log(this.getCategoryControls().value);
    console.log(JSON.stringify(this.getCategoryControls().value));
  }

}
