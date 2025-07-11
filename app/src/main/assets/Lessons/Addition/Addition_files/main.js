jQuery(document).ready(function () {

    jQuery('.spq_answer_block').click(function(e){
        e.preventDefault();
        //console.log(jQuery(this).val());
        var user_answer = jQuery(this).data('value');
        var question = jQuery(this).closest('.spq_question_wrapper');
        var answer = question.data('answer');
        if (!question.hasClass('answered')) {
            question.addClass('answered');
            if (answer == user_answer) {
                question.addClass('spq_question_correct');
                jQuery(this).addClass('spq_lgreen')
            } else {
                question.addClass('spq_question_incorrect');
                jQuery(this).addClass('spq_lred');

                question.find('.spq_answer_block[data-value="'+answer+'"]').addClass('spq_lgreen')
            }
        }
    })
});
