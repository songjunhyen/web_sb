<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
    // 각 #select 요소에 대해 처리하는 함수
    $('#select').each(function(index, item) {
        const itemElement = $(item);
        
        // data-boardid 속성 값 가져오기
        const defaultValue = itemElement.attr('data-dataValue').trim();
        
        // defaultValue가 0보다 크면 올바른 값으로 설정
        if (parseInt(defaultValue) > 0) { // parseInt() 함수를 사용하여 문자열을 숫자로 변환
            itemElement.val(defaultValue); 
        }
    });
</script>

<script>
/*
JavaScript 코드는 페이지에서 id가 "select"인 요소들에 대해 처리하는 함수를 정의한 것입니다. 코드를 분석해보겠습니다.

선택자와 각 요소에 대한 처리: $('#select').each(function(index, item) { ... });

$('#select'): jQuery를 사용하여 id가 "select"인 요소를 선택합니다. 이 요소들은 여러 개일 수 있으므로 .each() 함수를 사용하여 각각의 요소에 대해 처리를 반복합니다.
function(index, item) { ... }: 선택된 각 요소에 대해 실행할 함수를 정의합니다. 여기서 index는 현재 요소의 인덱스를, item은 각 요소 자체를 나타냅니다.
각 요소에서 data-dataValue 속성 값 가져오기: const defaultValue = itemElement.attr('data-dataValue').trim();

itemElement.attr('data-dataValue'): 선택된 요소의 data-dataValue 속성 값을 가져옵니다. .attr() 함수를 사용하여 요소의 속성 값을 읽어옵니다.
.trim(): 문자열 양 끝의 공백을 제거합니다.
조건에 따라 값 설정:

if (parseInt(defaultValue) > 0) { ... }: 가져온 defaultValue 값을 정수로 변환한 후, 그 값이 0보다 크면 실행됩니다.
itemElement.val(defaultValue);: 선택된 요소의 값(value)을 defaultValue로 설정합니다.
이 코드는 주어진 HTML에서 id가 "select"인 요소들의 data-dataValue 속성 값을 읽어와서, 그 값이 양수인 경우 해당 요소의 값을 그 값으로 설정하는 역할을 합니다. 이는 페이지에 특정 설정 값을 동적으로 적용하거나 초기화할 때 유용하게 사용
*/
</script>