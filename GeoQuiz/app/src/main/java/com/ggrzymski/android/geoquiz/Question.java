package com.ggrzymski.android.geoquiz;

public class Question
{
    private int text_res_id; // question text id

    private boolean answer_true; // question answer

    public Question(int textResId, boolean answerTrue)
    {
        text_res_id = textResId;
        answer_true = answerTrue;
    }

    public int getText_res_id()
    {
        return text_res_id;
    }

    public void setText_res_id(int text_res_id)
    {
        this.text_res_id = text_res_id;
    }

    public boolean isAnswer_true()
    {
        return answer_true;
    }

    public void setAnswer_true(boolean answer_true)
    {
        this.answer_true = answer_true;
    }
}
